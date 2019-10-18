
public class FileNode {
	private char[] fileName;
	private FileNode down;
	private ContentNode right;
	
	public FileNode(char[] fileName) {
		this.fileName=new char[11];
			for (int i = 0; i < fileName.length; i++) {
				this.fileName[i]=fileName[i];
			}
			down=null;
			right=null;
	}
	
	public char[] getFileName() {
		return fileName;
	}

	public void setFileName(char[] fileName) {
		this.fileName = fileName;
	}

	public FileNode getDown() {
		return down;
	}
	public void setDown(FileNode down) {
		this.down = down;
	}
	public ContentNode getRight() {
		return right;
	}
	public void setRight(ContentNode right) {
		this.right = right;
	}
	
}
