package EnergyMaze;
import enigma.core.Enigma;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import enigma.console.TextAttributes;



public class Screen {//Constructor
	//Alttaki kısmı kopyalarsanız bir konsol efekti var , kodları birleştirmeyi çalışırken basitte olsa birleştiremedik kodu bozmamak için bıraktık
	
	public enigma.console.Console cn = Enigma.getConsole("Energy Maze", 100, 50, true);
	
	//screen constructor
	// throws exception file operation için , maze i okuması için , silince hata veriyor
	public Screen() throws Exception{
		
		//ekranı yazdırdığımız yer
		// while true, ekranı veya onu sonsuza kadar döndürmeye yarar
		while(true){ 
			new EnergyMaze("maze.txt");
		}
	}
}
/**Efekt alt kısım
 * */

/*public class Screen {
	public enigma.console.Console cn = Enigma.getConsole("Energy Maze", 100, 50, true);
	
	
			private String input;
			Scanner scan = new Scanner(System.in);
			int count = 0;
			Color color = Color.WHITE;
		//	private Object[][] boardO = new Object[21][55];
		
			//private String whichMap = "A";

			public Screen() throws Exception {

				long start = System.currentTimeMillis();
				while (count < 51) {
					long end = 101 + System.currentTimeMillis();

					if (end - start >= 50) {
						start = end;
						count++;
					}
					Color renk = new Color(205, 92, 92);
					Color renk2 = new Color(255, 106, 106);
					Color renk3 = new Color(205, 51, 51);
					Color renk4 = new Color(255, 0, 0);
					Color renk5 = new Color(139, 0, 0);
					Color renk6 = new Color(255, 106, 106);
					Color renkasker = new Color(0, 139, 69);
					Color renkkahve = new Color(139, 115, 85);
					Color kirmizi = new Color(255, 48, 48);
					Color tenrengi = new Color(238, 207, 161);

					if (count < 44) {
						if (count % 2 == 1) {
							cn.getTextWindow().setCursorPosition(37 + count, 20);
							cn.getTextWindow().output("       _______",
									new enigma.console.TextAttributes(Color.YELLOW, color.BLACK));
							cn.getTextWindow().setCursorPosition(20 + count, 21);
							cn.getTextWindow().output("                      =|_______)\n",
									new enigma.console.TextAttributes(Color.yellow, color.BLACK));
							cn.getTextWindow().setCursorPosition(20 + count, 21);
							cn.getTextWindow().output("       .';-.;',`.;';.`\n",
									new enigma.console.TextAttributes(Color.red, color.BLACK));
						} else if (count % 2 == 0) {
							cn.getTextWindow().setCursorPosition(37 + count, 20);
							cn.getTextWindow().output("       _______",
									new enigma.console.TextAttributes(Color.YELLOW, color.BLACK));
							cn.getTextWindow().setCursorPosition(20 + count, 21);
							cn.getTextWindow().output("       '.-;;.,'.`';.;=`|_______)\n",
									new enigma.console.TextAttributes(Color.YELLOW, color.BLACK));
						}
					}
					if (count < 24) {
						cn.getTextWindow().setCursorPosition(0, 20);
						cn.getTextWindow().output("       ______\n", new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(15, 20);
						cn.getTextWindow().output("   _______  \n", new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(10, 20);
						cn.getTextWindow().output("   ,' `.\n", new enigma.console.TextAttributes(renkkahve, color.BLACK));

						cn.getTextWindow().setCursorPosition(3, 21);
						cn.getTextWindow().output("   (______(  -}___,,__)\n",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(10, 21);
						cn.getTextWindow().output("   (  -}\n", new enigma.console.TextAttributes(tenrengi, color.BLACK));

						cn.getTextWindow().setCursorPosition(15, 21);
						cn.getTextWindow().output("   ___,,__)\n", new enigma.console.TextAttributes(Color.gray, color.BLACK));

						////// KALE ALT KISIM///////
						cn.getTextWindow().setCursorPosition(65, 26);
						cn.getTextWindow().output("/8M%;:::;;:::::|                  |        `-------",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(66, 25);
						cn.getTextWindow().output("|;::::::::::::|       `'.________+-------\\   ``",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(66, 24);
						cn.getTextWindow().output("|;:;K`__,...;=\\_____,=``           %%%&     %#,---",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 23);
						cn.getTextWindow().output("  ,---`_,888`  ,.'''''`-.,|,|/!,--,.&\\|&\\-,|&#:::::",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 22);
						cn.getTextWindow().output("      ,--``8%,/    ',%||  | |=_-     =||-|-|%::::::",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 21);
						cn.getTextWindow().output("         ,   |/&_,_-|=||  | |=_-     =|-|-||:::::::",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 20);
						cn.getTextWindow().output("             |=_   -|=//^\\. |=_-     =||-|-|:::::::",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 19);
						cn.getTextWindow().output("             |=_   -|=_-_.  |=_-     =|-|-||:::::::",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 18);
						cn.getTextWindow().output("             |=LJ  -|=_-. . |=_-|_| =||-|-|:::::::",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 17);
						cn.getTextWindow().output("             |=||  -|=_-. . |=_-| |  =|-|-||::\\____",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 16);
						cn.getTextWindow().output("             |=/\\  -|=_-. . |=_-/^\\  =||-|-|::|____",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 15);
						cn.getTextWindow().output("             |=_   -;-='`. .|=_-     =|----T--,",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 14);
						cn.getTextWindow().output("            \\|=_  ' -=#J/..-|=_-     =|",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 13);
						cn.getTextWindow().output("           |,==Y``Y==,,__| \\L=_-`'   +J/`",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 12);
						cn.getTextWindow().output("           | ``    ^^    ||_,===TT`==,,_ |",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 11);
						cn.getTextWindow().output("           |`I|    || `==,|``   ^^   ``  |",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 10);
						cn.getTextWindow().output("            _ ,====, _    |I|`` ||  `|I `|",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(65, 9);
						cn.getTextWindow().output("                             ,.=,,==. ,,_",
								new enigma.console.TextAttributes(Color.gray, color.BLACK));

						cn.getTextWindow().setCursorPosition(0, 23);

						cn.getTextWindow().output("            .'  ,'  //                   \n",
								new enigma.console.TextAttributes(Color.green, color.BLACK));
						cn.getTextWindow().setCursorPosition(0, 24);
						cn.getTextWindow().output("           '    \\  //                   \n",
								new enigma.console.TextAttributes(Color.green, color.BLACK));
						cn.getTextWindow().setCursorPosition(0, 25);
						cn.getTextWindow().output("          '      `'/                     \n",
								new enigma.console.TextAttributes(Color.green, color.BLACK));
						cn.getTextWindow().setCursorPosition(0, 26);
						cn.getTextWindow().output("      ----`-------~-------------------   \n",
								new enigma.console.TextAttributes(Color.green, color.BLACK));
					}

					else if (count == 43) {
						cn.getTextWindow().setCursorPosition(76, 21);
						cn.getTextWindow().output(" _____.,-#%&$@%#&#~,._____.,'.`';.;          \n",
								new enigma.console.TextAttributes(kirmizi, color.BLACK));
						cn.getTextWindow().setCursorPosition(76, 20);
						cn.getTextWindow().output("          | ;  :|                            \n",
								new enigma.console.TextAttributes(kirmizi, color.BLACK));
					} else if (count == 44) {
						cn.getTextWindow().setCursorPosition(76, 18);
						cn.getTextWindow().output("       .-=||  | |=-.                                      \n",
								new enigma.console.TextAttributes(renk3, color.BLACK));
						cn.getTextWindow().setCursorPosition(76, 19);
						cn.getTextWindow().output("       `-=#$%&%$#=-'                            \n",
								new enigma.console.TextAttributes(renk3, color.BLACK));
					} else if (count == 45) {///
						cn.getTextWindow().setCursorPosition(76, 14);
						cn.getTextWindow().output("          | |   |                       \n",
								new enigma.console.TextAttributes(renk5, color.BLACK));

					} else if (count == 46) {
						cn.getTextWindow().setCursorPosition(76, 15);
						cn.getTextWindow().output("       .-=||  | |=-.                     \n",
								new enigma.console.TextAttributes(renk3, color.BLACK));

					} else if (count == 47) {
						cn.getTextWindow().setCursorPosition(76, 16);
						cn.getTextWindow().output("       `-=#$%&%$#=-'                        \n",
								new enigma.console.TextAttributes(renk3, color.BLACK));
						///
					} else if (count == 48) {
						cn.getTextWindow().setCursorPosition(76, 13);
						cn.getTextWindow().output("    ```--. . , ; .--'''                      \n",
								new enigma.console.TextAttributes(renk5, color.BLACK));
						cn.getTextWindow().setCursorPosition(76, 17);
						cn.getTextWindow().output("          | |   |                                \n",
								new enigma.console.TextAttributes(renk5, color.BLACK));
					} else if (count == 49) {
						cn.getTextWindow().setCursorPosition(76, 11);
						cn.getTextWindow().output("|                         |                       \n",
								new enigma.console.TextAttributes(renk5, color.BLACK));
						cn.getTextWindow().setCursorPosition(76, 12);
						cn.getTextWindow().output(" \\._                   _./             \n",
								new enigma.console.TextAttributes(renk5, color.BLACK));
					} else if (count == 50) {
						cn.getTextWindow().setCursorPosition(76, 8);
						cn.getTextWindow().output("     _.-^^---....,,--_                              \n",
								new enigma.console.TextAttributes(renk5, color.BLACK));
						cn.getTextWindow().setCursorPosition(76, 9);
						cn.getTextWindow().output(" _--                  --_                                \n",
								new enigma.console.TextAttributes(renk5, color.BLACK));
						cn.getTextWindow().setCursorPosition(76, 10);
						cn.getTextWindow().output("<                        >)                                  \n",
								new enigma.console.TextAttributes(renk5, color.BLACK));
					}
				}
				effectlessMenu();
			}

			public void effectlessMenu() throws Exception {
				Color renk = new Color(205, 92, 92);
				Color renk2 = new Color(255, 106, 106);
				Color renk3 = new Color(205, 51, 51);
				Color renk4 = new Color(255, 0, 0);
				Color renk5 = new Color(139, 0, 0);
				Color renk6 = new Color(255, 106, 106);
				Color renkasker = new Color(0, 139, 69);
				Color renkkahve = new Color(139, 115, 85);
				Color kirmizi = new Color(255, 48, 48);
				Color cyan = new Color(27, 139, 180);
				Color SARI = new Color(205, 155, 29);

				Color turkuaz = new Color(0, 255, 255);
				Color pudra = new Color(255, 240, 245);
				Color koyuten = new Color(255, 255, 0);
				Color darkgreen = new Color(193, 255, 193);
			}

		/*
		while(true){
			ClearScreen();
			cn.getTextWindow().setCursorPosition(5,5);
			cn.getTextWindow().output("\n                  ###### MENU ######", new enigma.console.TextAttributes(Color.magenta, Color.black));
			cn.getTextWindow().output("\n");
			cn.getTextWindow().output("\n   1)About the game",new enigma.console.TextAttributes(Color.cyan, Color.black));
			cn.getTextWindow().output("\n   2)Choose maze and play" ,new enigma.console.TextAttributes(Color.cyan, Color.black));
			cn.getTextWindow().output("\n");
			cn.getTextWindow().output("\n   Your choice: ",new enigma.console.TextAttributes(Color.cyan, Color.black));
			
			String choice = cn.readLine();
			
			Menu(choice);
		}
		
	}
	
	public void Menu(String choice) throws Exception{
		
		ClearScreen();
			
			if(choice.equals("1")){
				cn.getTextWindow().setCursorPosition(5, 5);
				cn.getTextWindow().output("There are two competitors: Human (H) and Computer (C). "
						+ "There are some energy points (*) in the game, which the players collect to increase "
						+ "their energy. There are also numbers from 1 to 4 in the game, which the human player "
						+ "can push and bring the same numbers together to get extra energy. Computer (C) "
						+ "always tries to catch Human (H). When it catches the human, the game ends. "
						+ "The aim of the game is having the highest energy point at the end.");
				cn.readLine();
			}
			
			else if(choice.equals("2")){
				cn.getTextWindow().setCursorPosition(5, 5);
				cn.getTextWindow().output("\n   1)Play with the original maze" ,new enigma.console.TextAttributes(Color.blue, Color.black));
				cn.getTextWindow().output("\n   2)Play with the our maze",new enigma.console.TextAttributes(Color.blue, Color.black));
				cn.getTextWindow().output("\n");
				cn.getTextWindow().output("\n   Your choice: ",new enigma.console.TextAttributes(Color.blue, Color.black));
				
				String maze = cn.readLine();
				ClearScreen();
			
				
				if(maze.equals("2")){
					new Game("C:\\maze.txt");
				}
			}
				}
	
			


		
				
				
		public void ClearScreen() throws Exception{
			
		for(int i=-10; i < 40; i++){
			cn.getTextWindow().setCursorPosition(0, i); cn.getTextWindow().output("                                                                                                    ");
		}
		
		}*/
	
		

	

	











