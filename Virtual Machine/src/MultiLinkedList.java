import java.awt.Color;
import java.util.Arrays;

import enigma.console.TextAttributes;

public class MultiLinkedList {//teknikte ram oluyor
	private FileNode head;
	private FileNode headAddress;

	public void addFileforAddress(char[] dataToAdd) {
		boolean error=false;
		if (headAddress == null) { //klasik ekleme işlemi , multi kısmına yani down a next e değil
			dataToAdd[10] = 0;
			FileNode newNode = new FileNode(dataToAdd);
			headAddress = newNode;
		} else {
			FileNode temp = headAddress;
			while (temp.getDown() != null) {
				temp = temp.getDown();
			}
				dataToAdd[10] = 0;
				FileNode newNode = new FileNode(dataToAdd);
				temp.setDown(newNode);
		}
	}
	public boolean addFile(char[] dataToAdd) {
		boolean error=false;
		if (head == null) {
			dataToAdd[10] = 0;
			FileNode newNode = new FileNode(dataToAdd);
			head = newNode;
			return error;
		} else {
			FileNode temp = head;
				temp=head;
				int pos=getPositionByName(dataToAdd);
				if (pos==-1) {
					dataToAdd[10] = 0;
					FileNode newNode = new FileNode(dataToAdd);
					newNode.setDown(temp);
					head=newNode;
				}
				//veri tekrarı
				else if (pos==-2) {
					error=true;
				}
				else {
					for (int i = 0; i < pos; i++) {
						temp=temp.getDown();
					}
					dataToAdd[10] = 0;
					FileNode newNode = new FileNode(dataToAdd);
					newNode.setDown(temp.getDown());
					temp.setDown(newNode);
				}
			return error;
		}
	}
	public int getPositionByName(char[] newValue){
		int pos;
		FileNode temp=head;
		pos=-1;
		while (temp!=null) {
			String tempFileStr="";
			String tempTempStr="";
			for (int i = 0; i < temp.getFileName().length-1; i++) {
				tempFileStr+=temp.getFileName()[i];
			}
			for (int i = 0; i < newValue.length-1; i++) {
				tempTempStr+=newValue[i];
			}
			if (tempFileStr.compareTo(tempTempStr)==0) {
				pos=-2;
				break;
			}else if (tempFileStr.compareTo(tempTempStr)<0) {
				pos++;
			}
			temp=temp.getDown();
		}
		return pos;
	}
	// disk i�in file index de�erini g�ncellemeye yarar
	public char[] updateFileIndex(char[] fileName) {
		FileNode temp = head;
		while (temp != null) {
			char[] tempFileName = new char[11];
			tempFileName = temp.getFileName();
			fileName[10] = tempFileName[10];
			if (Arrays.equals(fileName, temp.getFileName())) {
				return temp.getFileName();
			}
			temp = temp.getDown();
		}
		return null;
	}

	public char[] addContent(char[] fileName, char[] dataToAdd) {
		char[] changedPrev = new char[11];
		if (head == null) {
			System.out.println("Please add a file before content");
		} else {
			FileNode temp = head;
			while (temp != null) {
				char[] tempFileName = new char[11];
				tempFileName = temp.getFileName();
				fileName[10] = tempFileName[10];
				if (Arrays.equals(fileName, temp.getFileName())) {
					if ((int) temp.getFileName()[10] == 0) {
						changedPrev=temp.getFileName();
					}
					ContentNode temp2 = temp.getRight();
					if (temp2 == null) {
						dataToAdd[10] = 0;// e�er ilk ekleniyorsa next nodu
											// yoktur o y�zden 0
						ContentNode newNode = new ContentNode(dataToAdd);
						temp.setRight(newNode);
					} else {
						
						while (temp2.getNext() != null&&(int)(temp2.getContent()[10]) != 0) {
							temp2 = temp2.getNext();
						}
						changedPrev = temp2.getContent();
						while (temp2.getNext() != null) {
							temp2 = temp2.getNext();
						}
						
						// ------------------------
						dataToAdd[10] = 0;
						ContentNode newNode = new ContentNode(dataToAdd);
						temp2.setNext(newNode);
					}
				}
				temp = temp.getDown();
			}
		}
		return changedPrev;
	}
	public char[] addContentForAddress(char[] fileName, char[] dataToAdd) {
		char[] changedPrev = new char[11];
		if (headAddress == null) {
			System.out.println("Please add a file before content");
		} else {
			FileNode temp = headAddress;
			while (temp != null) {
				char[] tempFileName = new char[11];
				tempFileName = temp.getFileName();
				fileName[10] = tempFileName[10];
				if (Arrays.equals(fileName, temp.getFileName())) {
					if ((int) temp.getFileName()[10] == 0) {
						changedPrev=temp.getFileName();
					}
					ContentNode temp2 = temp.getRight();
					if (temp2 == null) {
						dataToAdd[10] = 0;// e�er ilk ekleniyorsa next nodu
											// yoktur o y�zden 0
						ContentNode newNode = new ContentNode(dataToAdd);
						temp.setRight(newNode);
					} else {
						while (temp2.getNext() != null) {
							temp2 = temp2.getNext();
						}
						changedPrev = temp2.getContent();
						// ------------------------
						dataToAdd[10] = 0;
						ContentNode newNode = new ContentNode(dataToAdd);
						temp2.setNext(newNode);
					}
				}
				temp = temp.getDown();
			}
		}
		return changedPrev;
	}
	public char[] insertContent(char[] fileName, char[] dataToAdd, int position) {
		char[] changedPrev = new char[11];
		if (head == null) {
			System.out.println("Please add a file before content");
		} else {
			FileNode temp = head;
			while (temp != null) {
				char[] tempFileName = new char[11];
				tempFileName = temp.getFileName();
				fileName[10] = tempFileName[10];
				if (Arrays.equals(fileName, temp.getFileName())) {
					if ((int) temp.getFileName()[10] == 0) {
						changedPrev=temp.getFileName();
					}
					ContentNode temp2 = temp.getRight();
					if (temp2 == null) {
						dataToAdd[10] = 0;// e�er ilk ekleniyorsa next nodu
											// yoktur o y�zden 0
						changedPrev=temp.getFileName();
						ContentNode newNode = new ContentNode(dataToAdd);
						temp.setRight(newNode);
					} else {
						int counter = 0;
						while (temp2.getNext() != null && position - 1 != counter) {
							counter++;
							temp2 = temp2.getNext();
						}
						// -----------------------
						/*
						 * bu k�s�mda eklenecek datan�n oncesindeki node'a next
						 * nodun konumunu veriyore
						 */
						// pozition-1==0 ise file sonunu de�i�tirece�iz de�ilse
						// content sonunu
						if (position - 1 == 0) {
							dataToAdd[10]=temp.getFileName()[10];
							changedPrev = temp.getFileName();
						} else if (temp2.getContent() != null) {
							dataToAdd[10]=temp2.getContent()[10];
							changedPrev = temp2.getContent();
						}
						// ------------------------
						while (temp2.getNext() != null) {
							temp2 = temp2.getNext();
						}
						if (!((int) dataToAdd[10] > 0 && (int) dataToAdd[10] < 31)) {
							dataToAdd[10] = 0;
						}
						ContentNode newNode = new ContentNode(dataToAdd);
						temp2.setNext(newNode);
					}
				}
				temp = temp.getDown();
			}
		}
		return changedPrev;
	}
	public char[] insertContentForAddress(char[] fileName, char[] dataToAdd, int position) {
		char[] changedPrev = new char[11];
		if (headAddress == null) {
			System.out.println("Please add a file before content");
		} else {
			FileNode temp = headAddress;
			while (temp != null) {
				char[] tempFileName = new char[11];
				tempFileName = temp.getFileName();
				fileName[10] = tempFileName[10];
				if (Arrays.equals(fileName, temp.getFileName())) {
					if ((int) temp.getFileName()[10] == 0) {
						changedPrev=temp.getFileName();
					}
					ContentNode temp2 = temp.getRight();
					if (temp2 == null) {
						dataToAdd[10] = 0;// e�er ilk ekleniyorsa next nodu
											// yoktur o y�zden 0
						ContentNode newNode = new ContentNode(dataToAdd);
						temp.setRight(newNode);
					} else {
						int counter = 0;
						while (temp2.getNext() != null && position - 1 != counter) {
							counter++;
							temp2 = temp2.getNext();
						}
						// -----------------------
						/*
						 * bu k�s�mda eklenecek datan�n oncesindeki node'a next
						 * nodun konumunu veriyore
						 */
						// pozition-1==0 ise file sonunu de�i�tirece�iz de�ilse
						// content sonunu
						if (position - 1 == 0) {
							changedPrev = temp.getFileName();
						} else if (temp2.getContent() != null) {
							changedPrev = temp2.getContent();
						}
						// ------------------------
						while (temp2.getNext() != null) {
							temp2 = temp2.getNext();
						}
						if (!((int) dataToAdd[10] > 0 && (int) dataToAdd[10] < 31)) {
							dataToAdd[10] = 0;
						}
						ContentNode newNode = new ContentNode(dataToAdd);
						temp2.setNext(newNode);
					}
				}
				temp = temp.getDown();
			}
		}
		return changedPrev;
	}
	public int sizeFile() {
		int count = 0;
		if (head == null) {
			System.out.println("linked list is empty");
		} else {
			FileNode temp = head;
			while (temp != null) {
				count++;
				temp = temp.getDown();
			}
		}
		return count;
	}

	public int sizeContent() {
		int count = 0;
		if (head == null) {
			System.out.println("linked list is empty");
		} else {
			FileNode temp = head;
			while (temp != null) {
				ContentNode temp2 = temp.getRight();
				while (temp2 != null) {
					count++;
					temp2 = temp2.getNext();
				}
				temp = temp.getDown();
			}
		}
		return (count + 1);
	}

	public int sizeAll() {
		int count = 0;
		if (head == null) {
			
		} else {
			FileNode temp = head;
			while (temp != null) {
				count++;
				ContentNode temp2 = temp.getRight();
				while (temp2 != null) {
					count++;
					temp2 = temp2.getNext();
				}
				temp = temp.getDown();
			}
		}
		return (count + 1);
	}

	public void deleteFile(char[] fileName) {
		if (head != null) {
			if (head.getDown() == null) {
				head = null;
			} else {

				FileNode temp = head;
				FileNode prev = null;
				while (temp != null) {
					char[] tempFileName = new char[11];
					tempFileName = temp.getFileName();
					fileName[10] = tempFileName[10];
					if (Arrays.equals(fileName, temp.getFileName())) {

						if (prev == null) {
							head = temp.getDown();
						} else {
							prev.setDown(temp.getDown());
						}
					} else {
						prev = temp;
					}

					temp = temp.getDown();
				}
			}
		} else {
			System.out.println("There is no file!");
		}
	}
	public void deleteFileForAddress(char[] fileName) {
		if (headAddress != null) {
			if (headAddress.getDown() == null) {
				headAddress = null;
			} else {

				FileNode temp = headAddress;
				FileNode prev = null;
				while (temp != null) {
					char[] tempFileName = new char[11];
					tempFileName = temp.getFileName();
					fileName[10] = tempFileName[10];
					if (Arrays.equals(fileName, temp.getFileName())) {

						if (prev == null) {
							headAddress = temp.getDown();
						} else {
							prev.setDown(temp.getDown());
						}
					} else {
						prev = temp;
					}

					temp = temp.getDown();
				}
			}
		} else {
			System.out.println("There is no file!");
		}
	}
	public char[] searchForDelete(char[] fileName,int position){
		if (head == null) {
			System.out.println("Linked list is empty");
		} else {
			FileNode temp = head;
			char[] tempStr = new char[11];
			while (temp != null) {
				tempStr = fileName;
				tempStr[10] = temp.getFileName()[10];
				if (Arrays.equals(fileName, temp.getFileName())) {
					ContentNode temp2 = temp.getRight();
					int counter=1;
					if (position!=1) {
						while (temp2.getNext() != null&&counter<position) {
							counter++;
							temp2 = temp2.getNext();
							}
					}
					return temp2.getContent();
						
					}
				temp = temp.getDown();
			}
		}
		return null;
	}
	public char[] deleteBlock(char[] fileName, int beginPos, int endPos) {
		char[] changedFileIndex = new char[11];
			if (head != null) {
				FileNode temp = head;
				while (temp != null) {
					char[] tempFileName = new char[11];
					tempFileName = temp.getFileName();
					fileName[10] = tempFileName[10];
					if (Arrays.equals(fileName, temp.getFileName())) {
						ContentNode temp2 = temp.getRight();
						if (temp2 != null) {
							int counter = 0;
							ContentNode prev=null;
							while (temp2.getNext() != null && beginPos - 1 != counter) {
								counter++;
								prev=temp2;
								temp2 = temp2.getNext();
							}
							counter = 0;
							do {
								counter++;
								if (prev == null) {
									changedFileIndex=temp.getFileName();
									changedFileIndex[10]=temp2.getContent()[10];
									head.setFileName(changedFileIndex);
									head.setRight(temp2.getNext());
								} else {
									changedFileIndex=prev.getContent();
									changedFileIndex[10]=temp2.getContent()[10];
									prev.setContent(changedFileIndex);
									prev.setNext(temp2.getNext());
								}
								temp2 = temp2.getNext();
							} while (counter != endPos - (beginPos-1) && temp2 != null);
						}
					}
					temp = temp.getDown();
				}
		} else {
			System.out.println("There is no file!");
		}
		return changedFileIndex;
	}
	public char[] deleteBlockForAddress(char[] fileName, int beginPos, int endPos) {
		char[] changedFileIndex = new char[11];
			if (headAddress != null) {
				FileNode temp = headAddress;
				while (temp != null) {
					char[] tempFileName = new char[11];
					tempFileName = temp.getFileName();
					fileName[10] = tempFileName[10];
					if (Arrays.equals(fileName, temp.getFileName())) {
						ContentNode temp2 = temp.getRight();
						if (temp2 != null) {
							int counter = 0;
							ContentNode prev=null;
							while (temp2.getNext() != null && beginPos - 1 != counter) {
								counter++;
								prev=temp2;
								temp2 = temp2.getNext();
							}
							counter = 0;
							do {
								counter++;
								if (prev == null) {
									changedFileIndex=temp.getFileName();
									changedFileIndex[10]=temp2.getContent()[10];
									headAddress.setFileName(changedFileIndex);
									headAddress.setRight(temp2.getNext());
								} else {
									changedFileIndex=prev.getContent();
									changedFileIndex[10]=temp2.getContent()[10];
									prev.setContent(changedFileIndex);
									prev.setNext(temp2.getNext());
								}
								temp2 = temp2.getNext();
							} while (counter != endPos - (beginPos-1) && temp2 != null);
						}
					}
					temp = temp.getDown();
				}
		} else {
			System.out.println("There is no file!");
		}
		return changedFileIndex;
	}
	public FileNode defrag(){
		int counter=2;
		if (head == null) {
			System.out.println("Linked list is empty");
		} else {
			FileNode temp = head;
			char[] tempStr = new char[11];
			while (temp != null) {
				tempStr= temp.getFileName();
				if ((int)tempStr[10]!=0) {
					tempStr[10]=(char)counter;
					temp.setFileName(tempStr);
				}
				counter++;
					ContentNode temp2 = temp.getRight();
					while (temp2 != null) {
						tempStr = temp2.getContent();
						if (temp2.getNext()!=null) {
							tempStr[10]=(char)counter;
							temp2.setContent(tempStr);
						}else {
							tempStr[10]=(char)0;
							temp2.setContent(tempStr);
						}
						counter++;
						temp2 = temp2.getNext();
					}
				temp = temp.getDown();
			}
			
		}
		return head;
	}
	public FileNode defragAddress(){
		int counter=2;
		if (headAddress == null) {
			System.out.println("Linked list is empty");
		} else {
			FileNode temp = headAddress;
			char[] tempStr = new char[11];
			while (temp != null) {
				tempStr= temp.getFileName();
				if ((int)tempStr[10]!=0) {
					tempStr[10]=(char)counter;
					temp.setFileName(tempStr);
				}
				counter++;
					ContentNode temp2 = temp.getRight();
					while (temp2 != null) {
						tempStr = temp2.getContent();
						if (temp2.getNext()!=null) {
							tempStr[10]=(char)counter;
							temp2.setContent(tempStr);
						}else {
							tempStr[10]=(char)0;
							temp2.setContent(tempStr);
						}
						counter++;
						temp2 = temp2.getNext();
					}
				temp = temp.getDown();
			}
			
		}
		return head;
	}
	public void displayContent(char[] fileName) {
		if (head == null) {
			System.out.println("Linked list is empty");
		} else {
			FileNode temp = head;
			while (temp != null) {
				char[] tempFileName = new char[11];
				tempFileName = temp.getFileName();
				fileName[10] = tempFileName[10];
				if (Arrays.equals(tempFileName, fileName)) {
					for (int i = 0; i < temp.getFileName().length; i++) {
						if (i == 10) {
							System.out.print((int) temp.getFileName()[i]);
						} else {
							System.out.print(temp.getFileName()[i]);
						}

					}
					System.out.print("-->");
					ContentNode temp2 = temp.getRight();
					while (temp2 != null) {
						for (int i = 0; i < temp2.getContent().length; i++) {
							if (i == 10) {
								System.out.print((int) temp2.getContent()[i]);
							} else {
								System.out.print(temp2.getContent()[i]);
							}
						}
						System.out.print(" ");
						temp2 = temp2.getNext();
					}
				}
				temp = temp.getDown();
				System.out.println();
			}
		}
	}

	public void display() {
		if (head == null) {
			System.out.println("Linked list is empty");
		} else {
			FileNode temp = head;
			while (temp != null) {
				for (int i = 0; i < temp.getFileName().length; i++) {
					if ((int) temp.getFileName()[i] >= 0 && (int) temp.getFileName()[i] <= 30) {
						System.out.print(" " + (int) temp.getFileName()[i]);
					} else {
						System.out.print(temp.getFileName()[i]);
					}
				}
				System.out.print("-->");
				ContentNode temp2 = temp.getRight();
				while (temp2 != null) {
					for (int i = 0; i < temp2.getContent().length; i++) {
						if ((int) temp2.getContent()[i] >= 0 && (int) temp2.getContent()[i] <= 30) {
							System.out.print(" " + (int) temp2.getContent()[i]);
						} else {
							System.out.print(temp2.getContent()[i]);
						}
					}
					System.out.print("\t");
					temp2 = temp2.getNext();
				}
				System.out.println();
				temp = temp.getDown();
			}
		}
		System.out.println();
	}
	public void displayAddress() {
		if (headAddress == null) {
			System.out.println("Linked list is empty");
		} else {
			FileNode temp = headAddress;
			while (temp != null) {
				for (int i = 0; i < temp.getFileName().length; i++) {
					if ((int) temp.getFileName()[i] >= 0 && (int) temp.getFileName()[i] <= 30) {
						System.out.print(" " + (int) temp.getFileName()[i]);
					} else {
						System.out.print(temp.getFileName()[i]);
					}
				}
				System.out.print("-->");
				ContentNode temp2 = temp.getRight();
				while (temp2 != null) {
					for (int i = 0; i < temp2.getContent().length; i++) {
						if ((int) temp2.getContent()[i] >= 0 && (int) temp2.getContent()[i] <= 30) {
							System.out.print(" " + (int) temp2.getContent()[i]);
						} else {
							System.out.print(temp2.getContent()[i]);
						}
					}
					System.out.print("\t");
					temp2 = temp2.getNext();
				}
				System.out.println();
				temp = temp.getDown();
			}
		}
		System.out.println();
	}
	// bize aranan de�erin nextnode indexini verecek
	public int searchFileandContent(String str) {
		if (head == null) {
			System.out.println("Linked list is empty");
			return 0;
		} else {
			FileNode temp = head;
			char[] tempStr = new char[11];
			while (temp != null) {
				tempStr = str.toCharArray();
				tempStr[10] = temp.getFileName()[10];
				if (temp.getFileName().toString().equals(str)) {
					return (int) temp.getFileName()[10];
				} else {
					ContentNode temp2 = temp.getRight();
					while (temp2 != null) {
						tempStr[10] = temp2.getContent()[10];
						if (Arrays.equals(temp2.getContent(), tempStr)) {
							return (int) temp2.getContent()[10];
						}
						temp2 = temp2.getNext();
					}
				}
				temp = temp.getDown();
			}
			return 0;
		}
	}
	public void restore(String[] file){
		if (head==null) {
			for (int i = 0; i < file.length; i++) {
				if (file[i].charAt(0)=='@') {
					addFileRestore(file[i].toCharArray());
					if ((int)file[i].charAt(10)!=0) {
						int pos=(int)file[i].charAt(10)-1;
						do {
							addContentRestore(file[i].toCharArray(), file[pos].toCharArray());
							pos=(int)file[pos].charAt(10)-1;
						} while (pos!=-1);
					}
				}
			}
		}
		else {
			System.out.println("Your system has data. For this reason you can not restore.(firstly, you should store)");
		}
	}
	public void restoreAddress(String[] file){
		if (headAddress==null) {
			for (int i = 0; i < file.length; i++) {
				if (file[i].charAt(0)=='@') {
					addFileRestoreAddress(file[i].toCharArray());
					if ((int)file[i].charAt(10)!=0) {
						int pos=(int)file[i].charAt(10)-1;
						do {
							addContentRestoreAddress(file[i].toCharArray(), file[pos].toCharArray());
							pos=(int)file[pos].charAt(10)-1;
						} while (pos!=-1);
					}
				}
			}
		}
		else {
			System.out.println("Your system has data. For this reason you can not restore.(firstly, you should store)");
		}
	}
	public void addFileRestore(char[] data){
		if (head == null) {
			FileNode newNode = new FileNode(data);
			head = newNode;
		} else {
			FileNode temp = head;
			while (temp.getDown() != null) {
				temp = temp.getDown();
			}
			FileNode newNode = new FileNode(data);
			temp.setDown(newNode);
		}
	}
	public void addFileRestoreAddress(char[] data){
		if (headAddress == null) {
			FileNode newNode = new FileNode(data);
			headAddress = newNode;
		} else {
			FileNode temp = headAddress;
			while (temp.getDown() != null) {
				temp = temp.getDown();
			}
			FileNode newNode = new FileNode(data);
			temp.setDown(newNode);
		}
	}
	public String getImagePath(char[] fileName){
		String retVal="";
		if (head == null) {
		} else {
			FileNode temp = head;
			while (temp != null) {
				char[] tempFileName = new char[11];
				tempFileName = temp.getFileName();
				fileName[10] = tempFileName[10];
				if (Arrays.equals(tempFileName, fileName)) {
					ContentNode temp2 = temp.getRight();
					while (temp2 != null) {
						for (int i = 0; i < temp2.getContent().length; i++) {
							if (i != 10) {
								retVal+=temp2.getContent()[i];
							}
						}
						temp2 = temp2.getNext();
					}
				}
				temp = temp.getDown();
			}
		}
		return retVal;
	}
	public void addContentRestore(char[] fileName,char[] data){
		FileNode temp = head;
		while (temp != null) {
			if (Arrays.equals(fileName, temp.getFileName())) {
				ContentNode temp2 = temp.getRight();
				if (temp2 == null) {
					ContentNode newNode = new ContentNode(data);
					temp.setRight(newNode);
				} else {
					while (temp2.getNext() != null) {
						temp2 = temp2.getNext();
					}
					ContentNode newNode = new ContentNode(data);
					temp2.setNext(newNode);
				}
			}
			temp = temp.getDown();
		}
	}
	public void addContentRestoreAddress(char[] fileName,char[] data){
		FileNode temp = headAddress;
		while (temp != null) {
			if (Arrays.equals(fileName, temp.getFileName())) {
				ContentNode temp2 = temp.getRight();
				if (temp2 == null) {
					ContentNode newNode = new ContentNode(data);
					temp.setRight(newNode);
				} else {
					while (temp2.getNext() != null) {
						temp2 = temp2.getNext();
					}
					ContentNode newNode = new ContentNode(data);
					temp2.setNext(newNode);
				}
			}
			temp = temp.getDown();
		}
	}
}
