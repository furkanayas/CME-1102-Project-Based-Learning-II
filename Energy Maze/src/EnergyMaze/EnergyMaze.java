package EnergyMaze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.core.Enigma;

public class EnergyMaze {
	//insan ve bilgisayar oluşturuldu
    Human h = new Human();
	Computer c = new Computer();
	public enigma.console.Console cn = Enigma.getConsole("Energy Maze", 100, 50, true);	
	int loopCount = 0;  // kaç kere while döngüsü olduğunu sayıyor , çünkü onu sleep la çarpıp hız hesabı yapıyor
	
	

	// throw exception fileoperataion okuması için
	public EnergyMaze(String text) throws Exception{			
		new Maze(text);
		Keyboard();

	}
	//keyboard constructor
	
	//hazır verilen mouse keyboard kodu
	public void Keyboard() throws InterruptedException{

		// ------ Standard code for mouse and keyboard ---****--- Do not change
		Maze.klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (Maze.keypr == 0) {
					Maze.keypr = 1;
					Maze.rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(Maze.klis);
		// -------------*****---------------------------------------

	
		Maze.maze[h.getx()][h.gety()] = 'H'; // humanın konumunu alındı 
		Maze.maze[c.getx()][c.gety()] = 'C';	// comp konumunu alındı 
		CircularQueue mazeegirenler = CircularQueue.girdial();	// dairesel q muzu 10 itemle doldurması için
		Maze.haritadakitem(mazeegirenler); // elementleri maze e alıyor ama yazdırmıyor , array dolduruluyor
		Maze.placeItem(); // her şeyi maze e yazdırıyor		
		Maze.controlOfMaze(); // aynı olan itemleri silip enerji veriyor
		
		yorumlar();//bilgi vermek için
		
		//bir şeyi sonsuzkez döndürmek istiyorsak while true yeterli
		while (true) {

			printEnergy();	//enerji yazma prose		
			printBackpack(h.boscantadoldurmaveyabosaltma(h)); //h içine girebileceğimiz
			printInput(mazeegirenler);  // girdileri , nesneleri yazıyor			
			Maze.refreshharitadakitem(mazeegirenler); // item silindiğinde yenisini aldığında tekrar itemleri yazdırıyor
			refreshMaze(); // This prints maze again.
			
			cn.getTextWindow().setCursorPosition(65, 7);
			cn.getTextWindow().output(Maze.haritadakitem[1].getzaman() + " <- T");		
			
			
			
			if (Maze.keypr == 1) { // tuşa baslırsa
				if (Maze.rkey == KeyEvent.VK_LEFT) {
					Maze.hMovements(h);									
				}
				if (Maze.rkey == KeyEvent.VK_RIGHT) {
					Maze.hMovements(h);			
				}
				if (Maze.rkey == KeyEvent.VK_UP) {
					Maze.hMovements(h);		
				}
				if (Maze.rkey == KeyEvent.VK_DOWN) {
				    Maze.hMovements(h);	
				}

				refreshMaze(); // her hareketten sonra ekranı tekrar yazıyor
				Maze.keypr = 0; // lac
			}
			loopCount++;
			decreasezamans();  // 100saniye sonra item siliniyor
			Maze.controlOfMaze(); // yakınında aynı varsa siliyor
			Thread.sleep(h.calculateSpeed()); // bekletme
		}
		
	}		
	
	//Eneryi ve eklerini yazdırmak için , yani enerji penceresini
	private void printEnergy()
	{
		cn.getTextWindow().setCursorPosition(64, 16);
		cn.getTextWindow().output(" Energy ");
		cn.getTextWindow().setCursorPosition(63, 17);
		cn.getTextWindow().output("╔═════════════╗");
		cn.getTextWindow().setCursorPosition(63, 18);
		cn.getTextWindow().output("║ H:  " + h.getEnergy() + "  <- ║   ");
		cn.getTextWindow().setCursorPosition(63, 19);
		cn.getTextWindow().output("║ C:  " + c.getEnergy() + "  <- ║   ");
		cn.getTextWindow().setCursorPosition(63, 20);
		cn.getTextWindow().output("╚═════════════╝");
	}
	
	
	
	//kuyruktaki ilk 10 luyu yazıyor
	private void printInput(CircularQueue input){		
		CircularQueue.girditazele(input);
		cn.getTextWindow().setCursorPosition(65, 2);
		cn.getTextWindow().output("NextItems");
		cn.getTextWindow().setCursorPosition(65, 3);
		cn.getTextWindow().output("<═════════");
		for (int i = 0; i < 10; i++){ //10 lu döngü
			
			cn.getTextWindow().setCursorPosition((65 + i), 4);	// çantadakileri yazdırmak için , peeklee yazdırıp kaydırıp tekrarlıyoruz		
			cn.getTextWindow().output(input.peek().getName());	
			input.enqueue(input.dequeue());
			
		}//kuyruk yazdırıldı
		cn.getTextWindow().setCursorPosition(65, 5);
		cn.getTextWindow().output("<═════════");
	}
	  
	//çantayı yazdırıyoruz
	private void printBackpack(Stack stc){ 													
		cn.getTextWindow().setCursorPosition(65, 8);
		cn.getTextWindow().output("Backpack");		
		Stack temp = new Stack(5);	
		for (int i = 1; i <= 5; i++){
			cn.getTextWindow().setCursorPosition(65, 8 + i);
			cn.getTextWindow().output("║   ║");			
		}
		cn.getTextWindow().setCursorPosition(65, 14);
		cn.getTextWindow().output("╚═══╝");
		while(!stc.isEmpty()){
			if (!temp.isFull()) temp.push(stc.pop());		
		}
		int i = 14;
		int size = temp.size();
		for (int j = 0; j < size; j++){	
			i--;
			if (!temp.isEmpty()){				
				Item obje = (Item)temp.peek();
			    cn.getTextWindow().setCursorPosition(67, i);
			    cn.getTextWindow().output(obje.getName());			
			    if (!stc.isFull()) stc.push(temp.pop());
			}
			else{				
				cn.getTextWindow().setCursorPosition(67, i);
			    cn.getTextWindow().output(' ');
			}
			
		}
		
	}
	
	  
	   //itemlerin zamanını düşürüyor 0 da siliyor
	   
	  private void decreasezamans() throws InterruptedException{		  
		  int zaman = 0;
		for (int i = 0; i < 20; i++){
			
			if (Maze.haritadakitem[i] != null){
				if (Maze.haritadakitem[i].getBackpack() == false){
		            zaman = (int) Maze.haritadakitem[i].getzaman();
			        if (h.calculateSpeed() == 400 && loopCount % 5 == 0)
			           zaman = zaman - 2 ;
			        else if (h.calculateSpeed() == 800 && loopCount % 5 == 0)
				       zaman = zaman - 4;
			    Maze.haritadakitem[i].setzaman(zaman);
		}	
		}
		}
		
		for (int i = 0; i < 20; i++){
			if (Maze.haritadakitem[i] != null){
			if (Maze.haritadakitem[i].getzaman() <= 0){
				Maze.maze[Maze.haritadakitem[i].getx()][Maze.haritadakitem[i].gety()] = ' ';							
			Maze.haritadakitem[i]= null;
			}
			
		}	
		}
		
	}
	 
	
	//Maze i yazdırmak için , maze classında maze arrayini klasik array yazdırma ile yazdırıyor
	 private void refreshMaze(){
		  cn.getTextWindow().setCursorPosition(0, 0);
			for(int i=0; i<21; i++){
				for(int j=0; j<55; j++){
					cn.getTextWindow().output(Maze.maze[i][j]);
				}
				cn.getTextWindow().output("\n");
			}
		  
	  }
	 //yorum yazmak için
	 private void yorumlar(){
		 cn.getTextWindow().setCursorPosition(1, 22);
		       cn.getTextWindow().output("╔═════════ Game Rules ═══════════════════════════════════════════════════╗");
		    cn.getTextWindow().output("\n ║ You must have at least 100 energies to take item in to the backpack    ║");
		    cn.getTextWindow().output("\n ║ Your speed will fall to half when your energy equals to 0              ║");
		    cn.getTextWindow().output("\n ║ Doubles gives 100 ,Triples gives 200, Quadruples gives 400 energy      ║");
		    cn.getTextWindow().output("\n ║ When the computer catch you , the game will be end                     ║");
		    cn.getTextWindow().output("\n ║											 						   ║");
		    cn.getTextWindow().output("\n ║Take Item       Put Item             Move                               ║");
		    cn.getTextWindow().output("\n ║   W                I                  ↑     					       ║");
		    cn.getTextWindow().output("\n ║ A H D            J H K			  ← H →						      ║");
		    cn.getTextWindow().output("\n ║   S                L                  ↓                                ║");
		    cn.getTextWindow().output("\n ╚════════════════════════════════════════════════════════════════════════╝");
	 }
	 
	

}
