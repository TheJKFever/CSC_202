
public class CharLLNode {
	private char data;
	private CharLLNode link;
	
	public CharLLNode(){
		data = '\0';
		link = null;
	}	
	public CharLLNode(char data) {
		this.data = data;
		link = null;
	}
	public char getChar() {
		return data;
	}
	public void setChar(char data) {
		this.data = data;
	}
	public CharLLNode getLink() {
		return link;
	}
	public void setLink(CharLLNode link) {
		this.link = link;
	}
	public char getCharToLowerCase(){
	    if(data>=97 && data<=122)
	        return (char)(data-32);
	    else
	        return data;
	}
}
