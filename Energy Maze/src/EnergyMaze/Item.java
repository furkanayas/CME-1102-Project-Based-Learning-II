package EnergyMaze;
//random import
import java.util.Random;
 
public class Item {
	private char name;
	private int zaman;
	private int x , y ;
	
	private boolean backpack;	// sırt çantasında mı değil mi için boolean
	
	Random random = new Random();// random için
	
	public Item(){ // 80: 40'*' + 10'1'+ 10'2' + 10'3' + 10'4'
		
		int ihtimal = random.nextInt(80);
		if(ihtimal <=40)
			this.name = '*'; //enerji %50
		else if (ihtimal >40 && ihtimal <50)
			this.name = '1';
		else if (ihtimal >= 50 && ihtimal < 60)
			this.name = '2';
		else if (ihtimal >= 60 && ihtimal < 70) // javada => olmaz >= olur
			this.name = '3';
		else
			this.name ='4';
	
		this.zaman = 100; // 100 saniye sonra kaybolacak
		this.x = 0; //henüz komut almamışken 0 atandı
		this.y = 0;
		this.backpack = false; //çantada değilken false , başlangıçta çantada değil
	
	}
	public boolean getBackpack(){
		return backpack;
	}
	public char getName(){
		return name;
	}
	public int getzaman(){
		return zaman;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	public void setx(int x){
		this.x = x;
	}
	public void sety(int y){
		this.y = y;
	}
	public void setzaman(int zaman){
		this.zaman = zaman;
	}
	public void setBackpack(boolean backpack){
		this.backpack = backpack;
	}
	
	// set etmeden get ile x çağırıp set le eklemek yerine , direk move la burda ekliyoruz
	public void movex(int eklenti){
		x = x + eklenti;
	}
	public void movey(int eklenti){
		y = y + eklenti;
	}
	

	

	
	
}
