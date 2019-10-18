import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Disk {
	private String[] diskStore;//store başlatman gerek
	
	public String[] getDiskStore() {
		return diskStore;
	}
	public void setDiskStore(String[] diskStore) {
		this.diskStore = diskStore;
	}
	//multilinklist olarak store paramentre alacak ve onu txt içerisine yazdıracak
	public Disk(){
		diskStore=new String[30];
		char space=(char)46;
		for (int i = 0; i < 30; i++) {
			diskStore[i]="";
			for (int j = 0; j < 10; j++) {
				diskStore[i]+=space;
			}
			
		}
	}
	public void store(){
		if (this.diskStore!=null) {
			try {
				PrintWriter pencil =new  PrintWriter("vdisk.txt","UTF-8");
				for (int i = 0; i < diskStore.length; i++) {
					pencil.println(diskStore[i]);
				}
				pencil.close();
			} catch (Exception e) {
				System.out.println("There is a problem on vdisk");
			}
		}
	}
	public String[] restore(){
		if (this.diskStore!=null) {
			File myFile=new File("vdisk.txt");//dosyam�z�n yeri
			Scanner fileIn;//dosyay� scanner ile cekece�iz
			try {
				fileIn=new Scanner(myFile);
				for (int i = 0; fileIn.hasNextLine(); i++) {
					diskStore[i]=fileIn.nextLine();
				}
						
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return diskStore;
	}
	public boolean addFile(char[] inputFileName)
	{
		String fileName="";
		for (int i = 0; i < inputFileName.length; i++) {
			fileName+=inputFileName[i];
		}
		for (int i = 0; i < this.diskStore.length; i++) {
			if (this.diskStore[i]!=null) {
				if ((int)this.diskStore[i].charAt(0)==46) {
					this.diskStore[i]="";
						this.diskStore[i]=fileName;
					return true;
				}
			}
		}
		return false;
	}
	public void changedValue(char[] str,int position)
	{
		this.diskStore[position]="";
		for (int i = 0; i < str.length; i++) {
			this.diskStore[position]+=str[i];
		}
		
	}
	public int addContent(char[] fileName,char[] content){
		boolean hasFile=false;
		int returnValue=-1;
		int filePosition=-1;
		for (int i = 0; i < diskStore.length; i++) {
			if (this.diskStore[i]!=null&&(int)this.diskStore[i].charAt(0)!=46) {
				fileName[10]=this.diskStore[i].charAt(this.diskStore[i].length()-1);
				if (Arrays.equals(this.diskStore[i].toCharArray(), fileName) ) {
					hasFile=true;
					if ((int)this.diskStore[i].charAt(this.diskStore[i].length()-1)<30) {
						filePosition=(i+1);
					}
					break;
				}
			}
		}
		if (hasFile) {
			for (int i = 0; i < diskStore.length; i++) {
				if (this.diskStore[i]!=null) {
					if ((int)this.diskStore[i].charAt(0)==46) {
						this.diskStore[i]="";
						for (int j = 0; j < content.length; j++) {
							this.diskStore[i]+=content[j];
						}
						if (content.length<11) {
							for (int j = 0; j < 11-content.length; j++) {
								this.diskStore[i]+=(char)46;
							}
						}
						returnValue=i;
						break;
					}
				}
			}
		}
		return returnValue;
	}
	
	//aranan de�erin konumunu d�nd�r�r(de�er yoksa -1 d�ner)
	public int search(char[] str){
		char[] tempStr=new char[11];
		for (int i = 0; i < tempStr.length; i++) {
			tempStr[i]=str[i];
		}
		tempStr[10]=' ';
		for (int i = 0; i < diskStore.length; i++) {
			if ((int)this.diskStore[i].charAt(0)!=46&&(int)this.diskStore[i].charAt(10)==0) {
				char[] tempAr=new char[11];
					tempAr=diskStore[i].toCharArray();
					tempAr[10]=' ';
				//buradaki ikinci ko�ul ayn� veriden tekrar girilirse index pointlerin kar��mas�n� �nlemek
				if (Arrays.equals(tempStr, tempAr)) {
					return i;
				}
			}
		}
		return -1;
	}
	public int searchBlock(char[] str){
		char[] tempStr=new char[11];
		for (int i = 0; i < tempStr.length; i++) {
			tempStr[i]=str[i];
		}
		tempStr[10]=' ';
		for (int i = 0; i < diskStore.length; i++) {
			if ((int)this.diskStore[i].charAt(0)!=46) {
				char[] deneme=diskStore[i].toCharArray();
				deneme[10]=' ';
				//buradaki ikinci ko�ul ayn� veriden tekrar girilirse index pointlerin kar��mas�n� �nlemek
				if (Arrays.equals(tempStr, deneme)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public void deleteFile(char[] fileName){
		int position=searchBlock(fileName);
		if (position!=-1) {
			while ((int)diskStore[position].charAt(10)!=0) {
				int tempPos=(int)diskStore[position].charAt(10);
				diskStore[position]="";
				for (int j = 0; j < 10; j++) {
					diskStore[position]+=(char)46;
				}
				position=(tempPos-1);
			}
			if ((int)diskStore[position].charAt(10)==0) {
				diskStore[position]="";
				for (int j = 0; j < 10; j++) {
					diskStore[position]+=(char)46;
				}
			}
		}
		
	}
	public void deleteBlock(char[] content){
		int position=-1;
		for (int i = 0; i < diskStore.length; i++) {
			if (Arrays.equals(diskStore[i].toCharArray(), content)) {
				position=i;
			}
		}
		if (position!=-1) {
				diskStore[position]="";
				for (int j = 0; j < 10; j++) {
					diskStore[position]+=(char)46;
				}
		}
		
	}
	public void defrag(FileNode head){
		
		if (head == null) {
			System.out.println("Linked list is empty");
		} else {
			//Clear Disk
			char space=(char)46;
			for (int i = 0; i < 30; i++) {
				diskStore[i]="";
				for (int j = 0; j < 10; j++) {
					diskStore[i]+=space;
				}
				
			}
			//---------
			FileNode temp = head;
			char[] tempStr = new char[11];
			int counter=0;
			while (temp != null) {
				diskStore[counter]="";
				for (int i = 0; i < temp.getFileName().length; i++) {
					diskStore[counter]+=temp.getFileName()[i];
				}
				counter++;
					ContentNode temp2 = temp.getRight();
						while (temp2 != null) {
							diskStore[counter]="";
							for (int i = 0; i < temp.getFileName().length; i++) {
								diskStore[counter]+=temp2.getContent()[i];
							}
							counter++;
							temp2 = temp2.getNext();
							}
				temp = temp.getDown();
			}
		}
	}
	public void print(){
		if (this.diskStore!=null) {
			System.out.print("01: ");
			for (int i = 0; i < this.diskStore.length; i++) {
				if ((int)this.diskStore[i].charAt(0)==46) {
					System.out.print(".......... 00"+"\t");
				}else {
					for (int j = 0; j < diskStore[i].length(); j++) {
						if (j==10) {
							if ((int)this.diskStore[i].charAt(j)<10) {
								System.out.print(" 0"+(int)this.diskStore[i].charAt(j));
							}
							else {
								System.out.print(" "+(int)this.diskStore[i].charAt(j));
							}
							
						}else {
							if ((int)this.diskStore[i].charAt(j)==46) {
								System.out.print(".");
							}
							else {
								System.out.print(this.diskStore[i].charAt(j));
							}
						}
							
					}
					System.out.print("\t");
				}
				if ((i+1)%5==0&&(i+1)!=30) {
					System.out.println();
					if (i+2==6) {
						System.out.print("0"+(i+2)+": ");
					}
					else {
						System.out.print((i+2)+": ");
					}
					
				}
				else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}
	public void printFile(char[] fileName){
		for (int i = 0; i < diskStore.length; i++) {
			if (diskStore[i].charAt(0)!='.') {
				fileName[10]=diskStore[i].charAt(10);
				if (Arrays.equals(diskStore[i].toCharArray(), fileName)) {
					if ((int)diskStore[i].charAt(10)!=0) {
						for (int j = 0; j < diskStore[i].length(); j++) {
							if (j==10) {
								System.out.print((int)diskStore[i].charAt(j));
							}else {
								System.out.print(diskStore[i].charAt(j));
							}
						}
						int pos=(int)diskStore[i].charAt(10)-1;
						do {
							System.out.print("->");
							for (int j = 0; j < diskStore[pos].length(); j++) {
								if (j==10) {
									System.out.print((int)diskStore[pos].charAt(j));
								}else {
									System.out.print(diskStore[pos].charAt(j));
								}
							}
							pos=(int)diskStore[pos].charAt(10)-1;
						} while (pos!=-1);
					}else {
						for (int j = 0; j < diskStore[i].length(); j++) {
							if (j==10) {
								System.out.print((int)diskStore[i].charAt(j));
							}else {
								System.out.print(diskStore[i].charAt(j));
							}
						}
						
					}
					System.out.println();
				}
				
			}
			
		}
	}
}
