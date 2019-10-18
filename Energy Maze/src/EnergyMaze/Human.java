package EnergyMaze;

import java.awt.event.KeyEvent;
//Human nesnesi
public class Human {
	private char name;
	private int energy;
	private double speed;
	private int x , y;
	Stack backpack = new Stack(5);

	Human() {
		int[] humanPlace = Maze.randomPlace(0); // randomPlace le rastgele kordinat alıyoruz 0 da ilk türü h için bakıyor.
		x = humanPlace[0]; //for x
		y = humanPlace[1];	// for y
		this.name = 'H'; // istenilen isim konabilir  düşündük
		energy = 200;		// ilk enerjisi 100 atandı
		

	}

	public  char getName() {
		return name;
	}

	public int getEnergy()
	{
		return energy;
	}	
	
	public double getSpeed() {
		return speed;
	}
	
	public void setx(int x){
		this.x = x;
	}
	
	public void sety(int y){
		this.y = y;
	}
	
	public void setEnergy(int energy){
		this.energy = energy;
	}
	// get ile ekleme çıkarma yapmak yerine bir yükselenerji fonk ile içine gireni ekle yaptık
	public void yükseltenerji(int eklenti) {
		energy = energy + eklenti;
	}
	//düşür
	public void düsürenerji(int eklenti) {
		if (energy != 0)
		energy = energy - eklenti;
	}

	public  int getx() {
		return x;
	}	

	public  int gety() {
		return y;
	}	
	
	
	public int calculateSpeed()
	{
		if (energy != 0)
			return (int)400;//milisaniyede 1 1/400
		else return (int)800;// 1/800   1/400>1/800 yani 800 olunca daha hızlı dönüyor
		
	}
	
	
	 //Çantaya ekleme ve çıkarma sırtçantasını oluşturup yansıtmak için , kuyruğu circularqueue da hallettik
	 
	
	//vk tuşu çağırır standart
	//almak için
	public Stack boscantadoldurmaveyabosaltma(Human h){
		if (Maze.rkey == KeyEvent.VK_W || Maze.rkey == KeyEvent.VK_A || Maze.rkey == KeyEvent.VK_S || Maze.rkey == KeyEvent.VK_D){
			// true almak için , false vermek için
		if (Maze.itemalıpverebilirmi(true, h, backpack) == true)
			Maze.doldurbosaltcantayı(true, backpack, h);
		}
		//vermek için
		else if (Maze.rkey == KeyEvent.VK_I || Maze.rkey == KeyEvent.VK_J || Maze.rkey == KeyEvent.VK_K || Maze.rkey == KeyEvent.VK_L){
			if (Maze.itemalıpverebilirmi(false, h, backpack) == true)
				Maze.doldurbosaltcantayı(false, backpack, h);
		}
		return backpack;
	}

}
