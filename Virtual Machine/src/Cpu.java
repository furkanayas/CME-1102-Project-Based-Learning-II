import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Cpu {
	private BufferedImage myPicture;
	public enigma.console.Console cn;
	private Disk disk;
	private MultiLinkedList ram;

	public Cpu() {
		cn = Enigma.getConsole("DeuCeng Virtual Machine", 195, 27, 18, 1);
		disk = new Disk();
		ram = new MultiLinkedList();
	}
	
	public void parseCommand(String cmd) {//splitedcmd arrayine aldığın cümleyi boşluklardan böl ve 10 lu str string arrayine sırayla yerleştir
		String[] splitedCmd = cmd.split(" ");
		String[] tempStr = new String[10];
		if (splitedCmd[0].equals("load")||splitedCmd[0].equals("loadw")) {//komutun ilk parçası bu ikisinden birine eşitse , load w konumda arar
			// load'dan sonra tek parametre alacağı için aşağıdaki kontrolü
			// yaptık
			if (splitedCmd.length == 2) {//2 kelie varsa load x.txt gibi olur yani okumak istemindedir
				File myFile = new File(splitedCmd[1]);//0 da komut 1 de txt var 
				Scanner scan;
				try {
					scan = new Scanner(myFile);//bir myfile yaratıyoruz içine txt yi alıp çekiyoruz
					for (int i = 0; scan.hasNextLine(); i++) {
						tempStr[i] = scan.nextLine();//lineları alıp tempstt lerin içine atıyor
					}

				} catch (FileNotFoundException e) {
					System.out.println("Please enter correct file name or path");
					System.out.println("You can get help with /help");//dosya yok
				}
			}else if (splitedCmd.length == 1&&splitedCmd[0].equals("loadw")) {
				FileDialog fd = new FileDialog(new JFrame(),"Choose A TXT File",FileDialog.LOAD);
				fd.setFile("*.txt");
				fd.setVisible(true);
				fd.setLocationRelativeTo(null);
				String filename = fd.getFile();
				String directory=fd.getDirectory();
				if (filename == null)
				  System.out.println("You cancelled the choice");
				else
				{
					File myFile = new File(directory+filename);
					Scanner scan;
					try {
						scan = new Scanner(myFile);
						for (int i = 0; scan.hasNextLine(); i++) {
							tempStr[i] = scan.nextLine();
						}
					} catch (Exception e) {
						System.out.println("Please enter correct file name or path");
						System.out.println("You can get help with /help");
					}
					

				}
			}
		}
		if (tempStr[0] != null) {
			for (int i = 0; i < tempStr.length; i++) {
				if (tempStr[i] != null) {
					System.out.println(tempStr[i]);
					splitedCmd = tempStr[i].split(" ");
					parsingCommand(splitedCmd);
				}

			}

		} else {
			parsingCommand(splitedCmd);
		}

	}

	public void parsingCommand(String[] splitedCmd) {
		switch (splitedCmd[0]) {
		case "print":
			if (splitedCmd.length == 2) {
				char[] fileName = new char[11];
				if (splitedCmd[1].length() < 11) {
					// başına @ ve sonuna özel karakter ekler
					fileName = editingFileName(splitedCmd[1]);
					if (fileName != null) {
						disk.printFile(fileName);
					}
				} else { //text name 11 den uzun olamadığı için create ederkende okurkende hata verir
					cn.setTextAttributes(new TextAttributes(Color.RED));
					System.out.println("Your login style is incorrect. You can get help with /help.");
					cn.setTextAttributes(new TextAttributes(Color.WHITE));
				}
			} else { //print ve filename olmalı  , farklı durumda girdi hatası verir
				cn.setTextAttributes(new TextAttributes(Color.RED));
				System.out.println("Your login style is incorrect. You can get help with /help.");
				cn.setTextAttributes(new TextAttributes(Color.WHITE));
			}
			break;
		case "create"://0 create se devamına filename olmalı 
			if (splitedCmd.length == 2) {
				char[] fileName = new char[11];
				if (splitedCmd[1].length() < 11) {
					fileName = editingFileName(splitedCmd[1]);//sonunu başını editler
					if (fileName != null) {
						boolean error = ram.addFile(fileName);
						if (error) {
							cn.setTextAttributes(new TextAttributes(Color.RED));
							System.out.println("There is a error.(Repating Data)");
							cn.setTextAttributes(new TextAttributes(Color.WHITE));
						} else {
							ram.addFileforAddress(fileName);
							disk.addFile(fileName);
							cn.setTextAttributes(new TextAttributes(Color.GREEN));
							System.out.println("Created File");
							cn.setTextAttributes(new TextAttributes(Color.WHITE));
						}

					}
				} else {
					cn.setTextAttributes(new TextAttributes(Color.RED));
					System.out.println("Your login style is incorrect. You can get help with /help.");
					cn.setTextAttributes(new TextAttributes(Color.WHITE));
				}

			} else {
				cn.setTextAttributes(new TextAttributes(Color.RED));
				System.out.println("Your login style is incorrect. You can get help with /help.");
				cn.setTextAttributes(new TextAttributes(Color.WHITE));
			}
			break;
		case "append"://dosyanın içine ek yapma , append file1 kelime1(zorunlu) kelime2 kelime3 ....en az 3 olan komut olmalı aksi taktirde else e gider help e yönlendirir
			if (splitedCmd.length >= 3) {
				char[] fileName = new char[11];
				if (splitedCmd[1].length() < 11) {
					fileName = editingFileName(splitedCmd[1]);
					if (fileName != null) {
						char[] appendChar = new char[11];
						int k = 0;
						String text = "";
						// cümle girdiyse her kelimeyi text içerisine atıyorum
						for (int i = 2; i < splitedCmd.length; i++) {
							text += splitedCmd[i];
							// en sonuna boşluk koymasın diye
							if (i + 1 != splitedCmd.length) {
								text += " ";
							}
						}
						// Ram veya disk boyutunun a�mas�n� engelliyoruz
						if (text.length() / 10 + ram.sizeAll() < 31) {
							// sonunda bo�luk varsa �zel karakter ekletiyorum
							if (text.length() % 10 != 0) {
								int miss = 11 - text.length() % 10;
								for (int j = 0; j < miss - 1; j++) {
									text += (char) 46;
								}
							}
							for (int i = 0; i < text.length(); i++) {
								appendChar[k] = text.charAt(i);
								k++;
								if (k % 10 == 0 || i == text.length() - 1) {

									char[] changedValue = new char[11];
									char[] changedValue2 = new char[11];
									appendChar[10] = (char) 0;
									// disk �zerine yeni gelen veriyi yaz�yoruz
									// ve positionunu al�yoruz
									int contPosition = disk.addContent(fileName, appendChar);
									contPosition++;
									// ram �zerinde index de�eri de�i�ecek
									// degeri bulup al�yoruz(veriyi eklemi�
									// olduk)
									changedValue = ram.addContent(fileName, appendChar);
									changedValue2=ram.addContentForAddress(fileName, appendChar);
									// burada ise de�i�en index de�erini disk
									// �zerinde update yap�yoruz
									if (contPosition != -1) {
										changedValue[10] = (char) contPosition;
										changedValue2[10] = (char) contPosition;
										if (changedValue != null && (int) changedValue[10] != 0) {
											int position = disk.search(changedValue);
											if (position != -1) {
												disk.changedValue(changedValue, position);
											}

										}
									}
									Arrays.fill(appendChar, ' ');// array�
																	// bo�altmak
																	// i�in
									k = 0;

								}
							}
							cn.setTextAttributes(new TextAttributes(Color.GREEN));
							System.out.println("Added content");
							cn.setTextAttributes(new TextAttributes(Color.WHITE));
						} else {
							cn.setTextAttributes(new TextAttributes(Color.RED));
							System.out.println("The system is overloaded");
							cn.setTextAttributes(new TextAttributes(Color.WHITE));
						}
					}

				} else {
					cn.setTextAttributes(new TextAttributes(Color.RED));
					System.out.println("Your login style is incorrect. You can get help with /help.");
					cn.setTextAttributes(new TextAttributes(Color.WHITE));
				}
			} else {
				cn.setTextAttributes(new TextAttributes(Color.RED));
				System.out.println("Your login style is incorrect. You can get help with /help.");
				cn.setTextAttributes(new TextAttributes(Color.WHITE));
			}
			break;
		case "insert":
			if (splitedCmd.length >= 3) {
				// 3ncu degerin say� olup olmad���na match ile bak�yoruz
				if (splitedCmd[1].length() < 11 && splitedCmd[2].matches("^-?\\d+$")) {
					char[] fileName = new char[11];
					fileName = editingFileName(splitedCmd[1]);
					if (fileName != null) {
						char[] appendChar = new char[11];
						int k = 0;
						String text = "";
						// c�mle girdiyse her kelimeyi text i�erisine at�yorum
						for (int i = 3; i < splitedCmd.length; i++) {
							text += splitedCmd[i];
							// en sonuna bo�luk koymas�n diye
							if (i + 1 != splitedCmd.length) {
								text += " ";
							}
						}
						int position = Integer.parseInt(splitedCmd[2]);
						if (text.length() / 10 + ram.sizeAll() < 31 && ram.sizeContent() >= position) {
							// sonunda bo�luk varsa nokta ekletiyorum
							if (text.length() % 10 != 0) {
								int miss = 11 - text.length() % 10;
								for (int j = 0; j < miss - 1; j++) {
									text += (char) 46;
								}
							}

							for (int i = 0; i < text.length(); i++) {
								appendChar[k] = text.charAt(i);
								k++;
								if (k % 10 == 0 || i == text.length() - 1) {
									char[] changedValue = new char[11];
									char[] changedValue2 = new char[11];
									appendChar[10] = (char) 0;
									// Ram d���nda add k�sm�ndaki i�lemler ile
									// ayn� say�l�r

									changedValue = ram.insertContent(fileName, appendChar, position);
									changedValue2=ram.insertContentForAddress(fileName, appendChar, position);
									appendChar[10] = changedValue[10];
									int contPosition = (disk.addContent(fileName, appendChar) + 1);
									// -----------------
									if (contPosition != -1) {

										if (changedValue != null) {
											int diskPosition = disk.searchBlock(changedValue);
											changedValue[10] = (char) contPosition;
											changedValue2[10]=(char) contPosition;
											if (diskPosition != -1) {
												disk.changedValue(changedValue, diskPosition);
											}

										}
									}
									// -----------------
									position++;
									Arrays.fill(appendChar, ' ');// array�
																	// bo�altmak
																	// i�in
									k = 0;
								}
							}
							cn.setTextAttributes(new TextAttributes(Color.GREEN));
							System.out.println("Inserted content");
							cn.setTextAttributes(new TextAttributes(Color.WHITE));
						} else {
							cn.setTextAttributes(new TextAttributes(Color.RED));
							System.out.println("There is a problem, ram is overloaded or position is wrong");
							cn.setTextAttributes(new TextAttributes(Color.WHITE));
						}
					}

				} else {
					cn.setTextAttributes(new TextAttributes(Color.RED));
					System.out.println("Your login style is incorrect. You can get help with /help.");
					cn.setTextAttributes(new TextAttributes(Color.WHITE));
				}
			} else {
				cn.setTextAttributes(new TextAttributes(Color.RED));
				System.out.println("Your login style is incorrect. You can get help with /help.");
				cn.setTextAttributes(new TextAttributes(Color.WHITE));
			}
			break;
		case "delete":
			if (splitedCmd[1].length() < 11) {
				char[] fileName = new char[11];
				fileName = editingFileName(splitedCmd[1]);
				if (fileName != null) {
					// file silme
					if (splitedCmd.length == 2) {
						ram.deleteFile(fileName);
						ram.deleteFileForAddress(fileName);
						disk.deleteFile(fileName);
						cn.setTextAttributes(new TextAttributes(Color.GREEN));
						System.out.println("deleted file");
						cn.setTextAttributes(new TextAttributes(Color.WHITE));
					}
					// block silme
					else if (splitedCmd.length == 4) {
						if (Integer.parseInt(splitedCmd[3]) - Integer.parseInt(splitedCmd[2]) >= 0) {
							char[] tempDel = new char[11];
							for (int i = Integer.parseInt(splitedCmd[2]); i < Integer.parseInt(splitedCmd[3])
									+ 1; i++) {
								tempDel = ram.searchForDelete(fileName, i);
								disk.deleteBlock(tempDel);
							}
							char[] updateIndex = new char[11];
							updateIndex = ram.deleteBlock(fileName, Integer.parseInt(splitedCmd[2]),
									Integer.parseInt(splitedCmd[3]));
							ram.deleteBlockForAddress(fileName, Integer.parseInt(splitedCmd[2]),
									Integer.parseInt(splitedCmd[3]));
							int position = disk.searchBlock(updateIndex);
							if (position != -1) {
								disk.changedValue(updateIndex, position);
								cn.setTextAttributes(new TextAttributes(Color.GREEN));
								System.out.println("deleted blocks");
								cn.setTextAttributes(new TextAttributes(Color.WHITE));
							}
						}
					}
				}

			} else {
				cn.setTextAttributes(new TextAttributes(Color.RED));
				System.out.println("Your login style is incorrect. You can get help with /help.");
				cn.setTextAttributes(new TextAttributes(Color.WHITE));
			}
			break;
		case "printdisk":
			disk.print();
			break;
		case "defrag":
			FileNode head = ram.defrag();
			ram.defragAddress();
			disk.defrag(head);
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.println("Done");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			break;
		case "store":
			disk.store();
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.println("Stored");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			break;
		case "restore":
			String[] tempDisk = disk.restore();
			ram.restore(tempDisk);
			ram.restoreAddress(tempDisk);
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.println("Restored");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			break;
		case "printram":
			if (splitedCmd.length == 2) {
				if (splitedCmd[1].equals("asc")) {
					ram.display();
				}
			} else {
				ram.displayAddress();
			}
			break;
		case "image":
			char[] fileName = new char[11];
			if (splitedCmd[1].length() < 11) {
				fileName = editingFileName(splitedCmd[1]);
				if (fileName != null) {
					String path=ram.getImagePath(fileName);
					if (!path.isEmpty()) {
						//image
						JDialog  frame = new JDialog ();
						frame.setSize(250,250);
						frame.setTitle("Your Image");
						try {
							myPicture = ImageIO.read(new File(path));
				            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				            frame.add(picLabel);
						} catch (Exception e) {
							cn.setTextAttributes(new TextAttributes(Color.RED));
							System.out.println("There is no image in your path");
							cn.setTextAttributes(new TextAttributes(Color.WHITE));
						}
						 frame.setLocationRelativeTo(null); 
						 frame.setAlwaysOnTop (true);
						 frame.setModalityType (ModalityType.APPLICATION_MODAL);
						frame.pack();
						frame.setVisible(true);
						//-----------------
					}
					
				}
			}
					
			break;
		case "open":
			char[] fileName1 = new char[11];
			if (splitedCmd[1].length() < 11) {
				fileName1 = editingFileName(splitedCmd[1]);
				if (fileName1 != null) {
					String path=ram.getImagePath(fileName1);
					if (!path.isEmpty()) {
						
						if (Desktop.isDesktopSupported()) {
							    try {
							        File myFile = new File(path);
							        Desktop.getDesktop().open(myFile);
							    } catch (IOException ex) {
							        // no application registered for PDFs
							    }
						}
						//-----------------
					}
					
				}
			}
			break;
		case "/help":
			System.out.println("Help Commands;");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("load file.txt");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print(" ---> load and run an executable batch file from harddisk\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("loadw");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print(" ---> Load with file manager\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("print filename");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print(" ---> print the content of the file on the screen\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("create filename ");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("--> create a file\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("append filename \"Hello World\"");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> append data to the end of the file (as a block/blocks)\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("insert filename \"Hello World\"");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> insert data into the file from the 3rd block (as a block/blocks)\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("delete filename ");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> delete file\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("delete filename 5 7 ");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> delete blocks from 5 to 7\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("printdisk ");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> print all vdisk on the screen\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("printram ");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> print all ram on the screen\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("printram asc");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> print all ram on the screen alphabetically\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("defrag ");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> defragment vdisk\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("store ");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> save vdisk to harddisk as a file (vdisk.txt)\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("restore ");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> restore vdisk from harddisk, clear RAM structures, and create appropriate new memory structures in the RAM\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("image filename ");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print("---> open image(if file contain a path)\n");
			cn.setTextAttributes(new TextAttributes(Color.GREEN));
			System.out.print("open filename");
			cn.setTextAttributes(new TextAttributes(Color.WHITE));
			System.out.print(" ---> open file(if file contain a path)\n");
			break;
		default:
			TextAttributes txtAtt = new TextAttributes(Color.RED);
			cn.setTextAttributes(txtAtt);
			System.out.println("Entering wrong command please try again");
			break;
		}
	}

	public char[] editingFileName(String inputFileName) {
		String addingStr = "@";
		boolean hasDot = false;
		for (int i = 0; i < inputFileName.length(); i++) {
			if ((int) inputFileName.charAt(i) == 46) {
				hasDot = true;
			}
		}
		if (!hasDot) {
			addingStr += inputFileName;
			// sonuna özel karakter ekliyoruz ki özel karakter olan yerlere
			// nokta
			// koyacağız
			if (addingStr.length() < 11) {
				int miss = 11 - addingStr.length();
				for (int i = 0; i < miss - 1; i++) {
					addingStr += (char) 46;
				}
			}
			char[] fileName = new char[11];
			if (addingStr.length() < 11) {
				for (int i = 0; i < addingStr.length(); i++) {
					fileName[i] = addingStr.charAt(i);
				}
			}
			return fileName;
		}

		return null;
	}
	public void clearScreen(){
		cn.getTextWindow().setCursorPosition(0, 0);
		for (int i = 0; i < 27; i++) {
			for (int j = 0; j < 114; j++) {
				cn.getTextWindow().setCursorPosition(j, i);
				cn.setTextAttributes(new TextAttributes(Color.BLACK,Color.WHITE));
				System.out.print(" ");
			}
		}
	}
	public void clearScreenBlack(){
		cn.getTextWindow().setCursorPosition(0, 0);
		for (int i = 0; i < 27; i++) {
			for (int j = 0; j < 114; j++) {
				cn.getTextWindow().setCursorPosition(j, i);
				cn.setTextAttributes(new TextAttributes(Color.WHITE,Color.BLACK));
				System.out.print(" ");
			}
		}
	}
}
