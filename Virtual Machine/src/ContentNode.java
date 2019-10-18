
public class ContentNode {
	private char[] content;
	private ContentNode next;
	
	public ContentNode(char[] content) {
		this.content=new char[11];
		for (int i = 0; i < content.length; i++) {
			this.content[i] = content[i];
		}
		this.next=null;
	}
	public char[] getContent() {
		return content;
	}
	public void setContent(char[] content) {
		this.content = content;
	}
	public ContentNode getNext() {
		return next;
	}
	public void setNext(ContentNode next) {
		this.next = next;
	}
	
}
