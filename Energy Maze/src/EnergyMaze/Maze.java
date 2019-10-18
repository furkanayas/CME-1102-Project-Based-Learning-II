package EnergyMaze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Random;

import enigma.core.Enigma;

public class Maze {
	
	public static Item[] haritadakitem = new Item[30]; // mazede ki 20 item için
	static Random random = new Random(); // her yerde kullanılablir random
	public  enigma.console.Console cn = Enigma.getConsole("Energy Maze", 100, 50, true);	
	public static char[][] maze  = new char[21][55]; // haritanın genişliği 21 satır  55 sütun
	static String str= "";
	public static KeyListener klis;
	// ------ Standard variables for mouse and keyboard ------
	public static int mousepr; // mouse pressed?
	public static int mousex, mousey; // mouse text coordinations.
	public static int keypr; // key pressed?
	public static int rkey; // key (for press/release)
	
	
	//okutmak için**
    Maze(String text){
    	//-----------------File Reader and Printer------------------------
    			try{
    				FileInputStream fStream = new FileInputStream(text);
    				DataInputStream dStream = new DataInputStream(fStream);
    				BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));
    				
    				for(int i=0; i<21; i++){
    					str = bReader.readLine();
    					for(int j=0; j< str.length(); j++){
    						maze[i][j] = str.charAt(j);
    					}
    				}
    				dStream.close();
    			}
    			
    			catch(Exception e){
    				cn.getTextWindow().output("\n Hata: " + e.getMessage());
    			}
    			
    			cn.getTextWindow().setCursorPosition(0, 0);
    			for(int i=0; i<21; i++){
    				for(int j=0; j<55; j++){
    					cn.getTextWindow().output(maze[i][j]);
    				}
    				cn.getTextWindow().output("\n");
    			}
    	
    }
	
 
	// eleman itilebilir mi diye bakıyor , tuşun yönüne göre boş m u kontrol ediyor

	public static boolean canPush(int elementNo){	
			if (rkey == KeyEvent.VK_LEFT){
				if (maze[haritadakitem[elementNo].getx()][haritadakitem[elementNo].gety() - 1] == ' ')				
				return true;					
			
			}
			else if (rkey == KeyEvent.VK_RIGHT){			
				if (maze[haritadakitem[elementNo].getx()][haritadakitem[elementNo].gety() + 1] == ' ')				
					return true;		
				
			}
			else if (rkey == KeyEvent.VK_UP){
				if (maze[haritadakitem[elementNo].getx() - 1][haritadakitem[elementNo].gety()] == ' ')				
					return true;	
				}
			
			else if (rkey == KeyEvent.VK_DOWN){
				if (maze[haritadakitem[elementNo].getx() + 1][haritadakitem[elementNo].gety()] == ' ')				
					return true;	
				}
			return false;
			}

	
	 // elemanlar aynı ise extra enerji kazanır

	
	private static boolean canGetEnergybyItem(int elementNo){
		int x = haritadakitem[elementNo].getx();
		int y = haritadakitem[elementNo].gety();	
		
		for (int i = 0; i < 20; i++){
			int nx = haritadakitem[i].getx();
			int ny = haritadakitem[i].gety();			
					
			/*
			 *  y 10 x10  11-1 10  10 10 sa yani eleman bir sağda ise enerji al yada
			 *  bir solda ise
			 *  yada bir altta
			 *  yada bir üstte ise enerji al
			 * */
		if (((y - 1 == ny && x == nx) || (y + 1 == ny && x == nx) || (y == ny && x - 1 == nx) || (y == ny && x + 1 == nx)) 
					&& haritadakitem[elementNo].getName() == haritadakitem[i].getName())
			return true;			
		
		
		}
		return false;
	}
	
	
	//itemin ve getirenin yok olması
	
	
	private static int diseppearItemandNeighboors(int elementNo){
		int count = 0;
		int x = haritadakitem[elementNo].getx();
		int y = haritadakitem[elementNo].gety();
		boolean diseppear = false;
		for (int i = 0; i < 20; i++){
			if (haritadakitem[i] != null){
			int nx = haritadakitem[i].getx();
			int ny = haritadakitem[i].gety();
			if (((y - 1 == ny && x == nx) || (y + 1 == ny && x == nx) || (y == ny && x - 1 == nx) || (y == ny && x + 1 == nx)) 
					&& haritadakitem[elementNo].getName() == haritadakitem[i].getName()){
				maze[nx][ny] = ' ';
				haritadakitem[i] = null;
				count++;
				diseppear  = true;
			}
		  }
		}
		if (diseppear == true){
		maze[x][y] = ' ';
		haritadakitem[elementNo] = null;
		}
		return count; 
	}

	/*
	 * boşluk varsa 1 birim yürür , enerji varsa o silinir enerji eklenir , enerji * silinir ve 
	 * 1 birim yürürIf there is a space ahead to human, only human moves ahead and human'S energy decrease one point.
	 */
	
	public static void hMovements(Human h){
		if (rkey == KeyEvent.VK_LEFT){
			boolean isPlay = false;
			for (int i = 0; i < 20; i++){
				if (h.gety() - 1 == haritadakitem[i].gety() && h.getx() == haritadakitem[i].getx() && haritadakitem[i].getName() != '*'){					
					if(Maze.canPush(i) == true){
						maze[haritadakitem[i].getx()][haritadakitem[i].gety()] = ' ';
						haritadakitem[i].movey(-1);
						maze[haritadakitem[i].getx()][haritadakitem[i].gety()] = haritadakitem[i].getName();
						maze[h.getx()][h.gety()] = ' ';
					    h.sety(h.gety() - 1);
					    maze[h.getx()][h.gety()] = 'H';
					    if (canGetEnergybyItem(i) == false)
					    h.düsürenerji(1);	
					    else{
					    	int count = diseppearItemandNeighboors(i);
						if (count == 1) h.yükseltenerji(100);
						else if (count == 2) h.yükseltenerji(200);
						else if (count == 3) h.yükseltenerji(400);
					    }
					
					isPlay = true;
					break;
					}					
				}
				else if (h.gety() - 1 == haritadakitem[i].gety() && h.getx() == haritadakitem[i].getx() && haritadakitem[i].getName() == '*'){
					maze[haritadakitem[i].getx()][haritadakitem[i].gety()] = ' ';
					haritadakitem[i] = null;
					maze[h.getx()][h.gety()] = ' ';
					h.sety(h.gety() - 1);
					maze[h.getx()][h.gety()] = 'H';
					h.yükseltenerji(25);
					isPlay = true;
					break;
				}			
					
			}
			if (maze[h.getx()][h.gety() - 1] == ' ' && isPlay == false){				
				maze[h.getx()][h.gety()] = ' ';
				h.sety(h.gety() - 1);
				maze[h.getx()][h.gety()] = 'H';
				h.düsürenerji(1);
				
			}
		}		
		else if (rkey == KeyEvent.VK_RIGHT){
			boolean isPlay = false;
			for (int i = 0; i < 20; i++){
				if (h.gety() + 1 == haritadakitem[i].gety() && h.getx() == haritadakitem[i].getx() && haritadakitem[i].getName() != '*'){
					
					if(canPush(i) == true){
						maze[haritadakitem[i].getx()][haritadakitem[i].gety()] = ' ';
						haritadakitem[i].movey(+1);
						maze[haritadakitem[i].getx()][haritadakitem[i].gety()] = haritadakitem[i].getName();
						maze[h.getx()][h.gety()] = ' ';
					    h.sety(h.gety() + 1);
					    maze[h.getx()][h.gety()] = 'H';
					    if (canGetEnergybyItem(i) == false)
					    h.düsürenerji(1);	
					    else{
					    	int count = diseppearItemandNeighboors(i);
							if (count == 1) h.yükseltenerji(100);
							else if (count == 2) h.yükseltenerji(200);
							else if (count == 3) h.yükseltenerji(400);
					    }
					}
					isPlay = true;
					break;
				}
				else if (h.gety() + 1 == haritadakitem[i].gety() && h.getx() == haritadakitem[i].getx() && haritadakitem[i].getName() == '*'){
					maze[haritadakitem[i].getx()][haritadakitem[i].gety()] = ' ';
					haritadakitem[i] = null;
					maze[h.getx()][h.gety()] = ' ';
					h.sety(h.gety() + 1);
					maze[h.getx()][h.gety()] = 'H';
					h.yükseltenerji(25);
					isPlay = true;
					break;
				}			
					
			}
			if (maze[h.getx()][h.gety() + 1] == ' ' && isPlay == false){				
				maze[h.getx()][h.gety()] = ' ';
				h.sety(h.gety() + 1);
				maze[h.getx()][h.gety()] = 'H';
				h.düsürenerji(1);
				
			}
		}
		else if (rkey == KeyEvent.VK_UP){
			boolean isPlay = false;
			for (int i = 0; i < 20; i++){
				if (h.gety() == Maze.haritadakitem[i].gety() && h.getx() - 1 == haritadakitem[i].getx() && haritadakitem[i].getName() != '*'){
					
					if(canPush(i) == true){
						maze[Maze.haritadakitem[i].getx()][haritadakitem[i].gety()] = ' ';
						haritadakitem[i].movex(-1);
						maze[haritadakitem[i].getx()][haritadakitem[i].gety()] = haritadakitem[i].getName();
						maze[h.getx()][h.gety()] = ' ';
					    h.setx(h.getx() - 1);
					    maze[h.getx()][h.gety()] = 'H';
					    if (canGetEnergybyItem(i) == false)
					    	h.düsürenerji(1);	
					    else{
					    	int count = diseppearItemandNeighboors(i);
							if (count == 1) h.yükseltenerji(100);
							else if (count == 2) h.yükseltenerji(200);
							else if (count == 3) h.yükseltenerji(400);
					    }
					}
					isPlay = true;
					break;
					
					
				}
				else if (h.gety() == haritadakitem[i].gety() && h.getx() - 1 == haritadakitem[i].getx() && haritadakitem[i].getName() == '*'){
					maze[Maze.haritadakitem[i].getx()][haritadakitem[i].gety()] = ' ';
					haritadakitem[i] = null;
					maze[h.getx()][h.gety()] = ' ';
					h.setx(h.getx() - 1);
					maze[h.getx()][h.gety()] = 'H';
					h.yükseltenerji(25);
					isPlay = true;
					break;
				}			
					
			}
			if (maze[h.getx() - 1][h.gety()] == ' ' && isPlay == false){				
				maze[h.getx()][h.gety()] = ' ';
				h.setx(h.getx() - 1);
				maze[h.getx()][h.gety()] = 'H';
				h.düsürenerji(1);
				
			}
		}	
		
		else if (rkey == KeyEvent.VK_DOWN){
			boolean isPlay = false;
			for (int i = 0; i < 20; i++){
				if (h.gety() == haritadakitem[i].gety() && h.getx() + 1 == haritadakitem[i].getx() && haritadakitem[i].getName() != '*'){					
					if(Maze.canPush(i) == true){						
						maze[Maze.haritadakitem[i].getx()][haritadakitem[i].gety()] = ' ';
						haritadakitem[i].movex(+1);
						maze[Maze.haritadakitem[i].getx()][haritadakitem[i].gety()] = haritadakitem[i].getName();
						maze[h.getx()][h.gety()] = ' ';
					    h.setx(h.getx() + 1);
					    maze[h.getx()][h.gety()] = 'H';
					    if (canGetEnergybyItem(i) == false)
					    	h.düsürenerji(1);
					    else{
					    	int count = diseppearItemandNeighboors(i);
							if (count == 1) h.yükseltenerji(100);
							else if (count == 2) h.yükseltenerji(200);
							else if (count == 3) h.yükseltenerji(400);
					    }
					}
					isPlay = true;
					break;
					
					
				}
				else if (h.gety() == haritadakitem[i].gety() && h.getx() + 1 == haritadakitem[i].getx() && haritadakitem[i].getName() == '*'){
					maze[Maze.haritadakitem[i].getx()][haritadakitem[i].gety()] = ' ';
					haritadakitem[i] = null;
					maze[h.getx()][h.gety()] = ' ';
					h.setx(h.getx() + 1);
					maze[h.getx()][h.gety()] = 'H';
					h.yükseltenerji(25);
					isPlay = true;
					break;
				}			
					
			}
			if (maze[h.getx() + 1][h.gety()] == ' ' && isPlay == false){				
				maze[h.getx()][h.gety()] = ' ';
				h.setx(h.getx() + 1);
				maze[h.getx()][h.gety()] = 'H';
				h.düsürenerji(1);
				
			}
		}
		
	}

	public static boolean itemalıpverebilirmi(boolean take, Human h, Stack stc){
		
		if (take == true){
			if (h.getEnergy() >= 100 && !stc.isFull()){
			for (int i = 0; i < 20; i++){		
				//bir üstündeki item , itemin y si 10 human 10 sa ve human x 9 item x 10 ise item w konumundadır
				if (rkey == KeyEvent.VK_W){
						if (h.gety() == haritadakitem[i].gety() && h.getx() - 1 == haritadakitem[i].getx())
							return true;
					}
				//bir solunda
					else if (rkey == KeyEvent.VK_A){
						if (h.gety() - 1 == haritadakitem[i].gety() && h.getx() == haritadakitem[i].getx())
							return true;
					}
				//bir altında
					else if (rkey == KeyEvent.VK_S){
						if (h.gety() == haritadakitem[i].gety() && h.getx() + 1 == haritadakitem[i].getx())
							return true;
					}
				//bir sağında
					else if (rkey == KeyEvent.VK_D){
						if (h.gety() + 1 == haritadakitem[i].gety() && h.getx() == haritadakitem[i].getx())
							return true;
					}				
			}
		  }
			
		}
		//take true ise al false ise ver durumu
		else if(take == false){
			if (!stc.isEmpty()){
				// IJKLM 
				
			if (rkey == KeyEvent.VK_I){
				if (maze[h.getx() - 1][h.gety()] == ' ') return true;
			}
			else if (rkey == KeyEvent.VK_J){
				if (maze[h.getx()][h.gety() - 1] == ' ') return true;
			}
			else if (rkey == KeyEvent.VK_K){
				if (maze[h.getx() + 1][h.gety()] == ' ') return true;
			}
			else if (rkey == KeyEvent.VK_L){
				if (maze[h.getx()][h.gety() + 1] == ' ') return true;
			}
			}
			
		}
		return false;
	}
	
	 // play true ise çantaya alıyor false ise haritaya atıyor
	 
	public static void doldurbosaltcantayı(boolean take, Stack stc, Human h){
		if (take == true){
			boolean play = false;
			for (int i = 0; i < 20; i++){				
				if (rkey == KeyEvent.VK_W){
					if (h.getx() - 1 == haritadakitem[i].getx() && h.gety() == haritadakitem[i].gety()){
						maze[h.getx() - 1][h.gety()] = ' ';
						play = true;
					}
				}
				else if (rkey == KeyEvent.VK_A){
                    if (h.getx() == haritadakitem[i].getx() && h.gety() - 1 == haritadakitem[i].gety()){
                    	maze[h.getx()][h.gety() - 1] = ' ';
                        play = true;
					}
				}
				else if (rkey == KeyEvent.VK_S){
                    if (h.getx() + 1 == haritadakitem[i].getx() && h.gety() == haritadakitem[i].gety()){
						maze[h.getx() + 1][h.gety()] = ' ';
						play = true;
					}
				}
				else if (rkey == KeyEvent.VK_D){
                    if (h.getx() == haritadakitem[i].getx() && h.gety() + 1 == haritadakitem[i].gety()){
						maze[h.getx()][h.gety() + 1] = ' ';
						play = true;
					}
				}
				if (play == true){
					haritadakitem[i].setBackpack(true);
					haritadakitem[i].setx(0);
					haritadakitem[i].sety(0);
					Item obje = haritadakitem[i];
					//alınacak itemi objeye eşitle , stack a push et ve humandan 100 enerji düşür
					stc.push(obje);
					h.düsürenerji(100);
					break;
				}
			}			

		}
		else if (take == false){
			//false verme durumunda , objemiz item türünde onu item olarak parslayıp türünü tanıtıp poplarız ve i inci item obje olur
			int index = 0;
			Item obje = (Item) stc.pop();
			for (int i = 0; i < 20; i++){
				if (haritadakitem[i] == obje)
					index = i;
								
				}
			// yeride tuşlara göre çıkar
			//ı bir üste çıkması , iteme humana göre bir üstte olacak şekilde kordinat set ederiz ve maze e ekleriz
			
			haritadakitem[index].setBackpack(false);			
			if (rkey == KeyEvent.VK_I){
				haritadakitem[index].setx(h.getx() - 1);
				haritadakitem[index].sety(h.gety());
				maze[h.getx() - 1][h.gety()] = haritadakitem[index].getName();
			}
			else if (rkey == KeyEvent.VK_J){
				haritadakitem[index].setx(h.getx());
				haritadakitem[index].sety(h.gety() - 1);
				maze[h.getx()][h.gety() - 1] = haritadakitem[index].getName();
			}
			else if (rkey == KeyEvent.VK_K){
				haritadakitem[index].setx(h.getx() + 1);
				haritadakitem[index].sety(h.gety());
				maze[h.getx() + 1][h.gety()] = haritadakitem[index].getName();
			}
			else if (rkey == KeyEvent.VK_L){
				haritadakitem[index].setx(h.getx());
				haritadakitem[index].sety(h.gety() + 1);
				maze[h.getx()][h.gety() + 1] = haritadakitem[index].getName();
			}
			//item kaybolma durumu geçerli ise count da yani item sayısına göre enerji verir , 1 item yanına kaç item koyuyorsak ona göre
			int count = diseppearItemandNeighboors(index);
			if (count == 1) h.yükseltenerji(100);
			else if (count == 2) h.yükseltenerji(200);
			else if (count == 3) h.yükseltenerji(400); 
		
		}
	}
	
	// kuyruk boşken method la yeni girdiler alıyor sonra onları çıkartırken her birini bir indexte bir array elemanı nesnesi yapıyor
      public static void haritadakitem(CircularQueue queue){		
		for (int i = 0; i < 20; i++){
			if (queue.isEmpty())
				CircularQueue.girditazele(queue);
			haritadakitem[i] = queue.dequeue();							
			}		
		}
      
  	
    
  	  public static void refreshharitadakitem(CircularQueue queue){
		for (int i = 0; i < 20; i++){
			//haritada eksik eleman arıyor
			
			if (haritadakitem[i] == null){
				if (queue.isEmpty())
					CircularQueue.girditazele(queue);
				haritadakitem[i] = queue.dequeue();				
				int[] place = randomPlace(2);
				haritadakitem[i].setx(place[0]);
			    haritadakitem[i].sety(place[1]);
				maze[place[0]][place[1]] = haritadakitem[i].getName();
				
				}		
				
			}				
		}
	
	
	
  	 // gelen random place
	  public static void placeItem(){
		
		for (int i = 0; i < 20; i++){
			int[] place = randomPlace(2);
			haritadakitem[i].setx(place[0]);
			haritadakitem[i].sety(place[1]);
		    maze[place[0]][place[1]] = haritadakitem[i].getName();			
				
			    
			}		
		
		}	
	  
	  
	  //itemler yer bulmak için random
	public static int[] randomPlace(int forWhich){
		int randomX = 0;
		int randomY = 0;
		int[] place = new int[2];
		boolean suitable = false;
		while (suitable == false){
			randomX = random.nextInt(21);
			randomY = random.nextInt(55);
			if (isSuitable(randomY, randomX, forWhich) == true)
				suitable = true;			
		}
		place[0] = randomX;
		place[1] = randomY;
		return place;	
		// uygunsa değeri place olarak döndür ve yolla eğer değilse tekrarla , suitable true olana kadar
	}		
	
	
	//forwhich 0 olunca H için yer arar , 1 olunca C için ,2olunca nesneler için
	private static boolean isSuitable(int y, int x, int forWhich){
		boolean suitable = false;
		if (forWhich == 0){
			for (int i = 1; i < 20; i++){
				for (int j = 1; j < 54; j++){
					//maze i gez # olmayan yerleri true al bu h koymak için 0
					if (y == j && x == i && maze[i][j] != '█')
						suitable = true;					
				}				
			  }
		}
		else if (forWhich == 1){
			for (int i = 1; i < 20; i++){
				// c koymak için 1
				for (int j = 1; j < 54; j++){
					if (y == j && x == i && maze[i][j] != '█' && maze[i][j] != 'H')
						suitable = true;	
					}			
			  }					
		}
		else if (forWhich == 2){
			for (int i = 1; i < 20; i++){
				// item ve enerji koymak için 2
				for (int j = 1; j < 54; j++){
					if (y == j && x == i && maze[i][j] != '█' && maze[i][j] != 'H' && maze[i][j] != 'C' && maze[i][j] != '*' &&
							maze[i][j] != '1' && maze[i][j] != '2' && maze[i][j] != '3' && maze[i][j] != '4')
						suitable = true;	
					}			
			  }
			
		}
		
		return suitable;
	}	
	
	
	//arka arkaya alt alta aynı eleman olmasını engeller.**
	public static void controlOfMaze(){
		for (int i = 0; i < 20; i++){
			for (int j = 0; j <20; j++){
				if (haritadakitem[i] != null && haritadakitem[j] != null){
					if (((haritadakitem[i].getx() == haritadakitem[j].getx() && haritadakitem[i].gety() - 1  == haritadakitem[j].gety())||
						(haritadakitem[i].getx() == haritadakitem[j].getx() && haritadakitem[i].gety() + 1  == haritadakitem[j].gety()) ||
						(haritadakitem[i].getx() - 1 == haritadakitem[j].getx() && haritadakitem[i].gety()  == haritadakitem[j].gety()) ||
						(haritadakitem[i].getx() + 1 == haritadakitem[j].getx() && haritadakitem[i].gety()  == haritadakitem[j].gety())) &&
						haritadakitem[i].getName() == haritadakitem[j].getName()){
						maze[haritadakitem[i].getx()][haritadakitem[i].gety()] = ' ';
						maze[haritadakitem[j].getx()][haritadakitem[j].gety()] = ' ';
						haritadakitem[i] = null;
						haritadakitem[j] = null;
					}
				
			}
		}
	}
	}
	
	
	
	
}
	
	
	
	
	

	
	
	


