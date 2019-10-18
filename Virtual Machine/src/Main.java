import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import enigma.console.TextAttributes;

public class Main {
	static Cpu screen;
	static int selectedMenu=0;
	public static void main(String[] args) {
		screen = new Cpu();
		String[] upMenu=new String[3];
		upMenu[0]="Open Virtual Machine";
		upMenu[1]="About Machine";
		upMenu[2]="Exit";
		createKeyListener();
		drawMenu(upMenu);
		boolean isSelect=true;
		while(isSelect) {
			 if(keypr==1) {    // if keyboard button pressed
				 screen.clearScreen();
	        	  switch (rkey) {
	        	  case KeyEvent.VK_UP:
	        		  if (selectedMenu>0) {
						selectedMenu--;
					}
	        		  break;
	        	  case KeyEvent.VK_DOWN:
	        		  if (selectedMenu<2) {
							selectedMenu++;
						}
	        		  break;
	        	  case KeyEvent.VK_ENTER:
	        		   	 switch (selectedMenu) {
	        		   	 case 0://New Game
	        		   		 isSelect=false;
	        		   		 break;
						case 1:
							screen.cn.getTextWindow().setCursorPosition(12, 4);
							System.out.println("The aim of the project is to implment a simplified virtual machine partially.");
							screen.cn.getTextWindow().setCursorPosition(12, 5);
							System.out.println("In the RAM, file structures include name, size and diskstart.");
							screen.cn.getTextWindow().setCursorPosition(12, 6);
							System.out.println("Commands and files to be worked on must be kept on RAM for quick access.");
							screen.cn.getTextWindow().setCursorPosition(12, 7);
							System.out.println("All files must be synchronized in RAM and vdisk.");
							screen.cn.getTextWindow().setCursorPosition(12, 8);
							System.out.println("The vdisk consist of 30 blocks.");
							screen.cn.getTextWindow().setCursorPosition(12, 9);
							System.out.println("Each block contains 11 byte/characters.");
							screen.cn.setTextAttributes(new TextAttributes(Color.WHITE,Color.BLACK));
							screen.cn.getTextWindow().setCursorPosition(25, 10);
							System.out.println("BACK");
							Scanner scan2=new Scanner(System.in);
							scan2.nextLine();
							break;
						case 2://exit
							System.exit(0);//oyunu kapatır
							break;
						}
	        		  break;

	        	  }
		          keypr=0;    // last action  
		          drawMenu(upMenu);
			 }
			 try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		
		screen.clearScreenBlack();
		screen.cn.getTextWindow().setCursorPosition(0, 0);
		do {
			
			TextAttributes txtAtt=new TextAttributes(Color.YELLOW);
			screen.cn.setTextAttributes(txtAtt);
			System.out.print("Command>");
			txtAtt=new TextAttributes(Color.WHITE);
			screen.cn.setTextAttributes(txtAtt);
			String command = screen.cn.readLine();
			screen.parseCommand(command.toLowerCase().trim());
		} while (true);
		
	}
	static int keypr;
	static int rkey; 
	static KeyListener klis; 
	static void createKeyListener(){
		klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      screen.cn.getTextWindow().addKeyListener(klis);
	};
	static void drawMenu(String[] inMenu){
		screen.cn.getTextWindow().setCursorPosition(0, 0);
		for (int i = 0; i < 27; i++) {
			for (int j = 0; j < 114; j++) {
				screen.cn.getTextWindow().setCursorPosition(j, i);
				screen.cn.setTextAttributes(new TextAttributes(Color.BLACK,Color.WHITE));
				System.out.print(" ");
			}
		}
		for (int i = 0; i <inMenu.length ; i++) {
			screen.cn.getTextWindow().setCursorPosition(40,i+4);
			if (i==selectedMenu) {
				screen.cn.setTextAttributes(new TextAttributes(Color.WHITE,Color.BLACK));
				System.out.print(inMenu[i]);
			}
			else {
				screen.cn.setTextAttributes(new TextAttributes(Color.BLACK,Color.WHITE));
				System.out.print(inMenu[i]);
			}
		}
	}
}
