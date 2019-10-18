package EnergyMaze;
// class açtık ama yetiştiremedik ekleyemedik , ekranda rastgele bir yerde konum alıyor o kadar
public class Computer {
	private char name;
	private int energy;
	private int speed;
	private int x;
	private int y; 
	

	Computer() {
		int[] pcyeri = Maze.randomPlace(1);
		x = pcyeri[0];
		y = pcyeri[1];	
		name = 'C';
		energy = 100;
		speed = 400;		
	}
	
	public char getName(){
		return name;
	}
	
	public long getSpeed(){
		return speed;
	}
	
	public int getEnergy()
	{
		return energy;
	}
	
	public void setx(int x){
		this.x = x;
	}
	
	public void sety(int y){
		this.y = y;
	}
	
	public int getx(){
		return x;
	}
	
	public int gety(){
		return y;
	}	
	// enerjiyi dışarda seylerle hesaplatmak yerine yine method yazdık ama kullanmadık
	public void Enerjiarttırma(int artıs) {
		energy = energy + artıs;
	}

	public void Enerjiazaltma(int azalma) {
		energy = energy + azalma;
	}

	
	

}
