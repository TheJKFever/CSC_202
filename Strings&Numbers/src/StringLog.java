import java.util.Scanner;

public class StringLog implements StringLogInterface {

	static Scanner keyboard = new Scanner(System.in);
	private CharLLNode head, current;
	private int length;

//Constructors
	public StringLog(){ //Uses keyboard to take in String
		head=null;
		current=null;
		length=0;
		String data = keyboard.nextLine();
		readInStringLog(data);		
	}
	public StringLog(String data) { //Creates StringLog from String
		head=null;
		current=null;
		length=0;
		readInStringLog(data);
	}
//PUBLIC METHODS	
	@Override
	public boolean search(String key) { //start at the beginning of the StringLog, returns true if contains key
		return search(key, 0);
	}
	@Override
	public boolean search(String key, int startAt) { //startAt is inclusive - 0 starts from the beginning of the string
		StringLog tempSearch = new StringLog(key); //creates StringLog with given String key
		int lengthLeftOver = length-startAt; //created to make sure not to check for 5 characters if there's only 2 left to check...
		current = getNodeAtIndex(startAt); //sets current to node at index startAt
		CharLLNode lastMatchIndex = current; //creates temp CharLLNode for iteration
		while (lengthLeftOver>=tempSearch.length()){ 
			current = lastMatchIndex;
			for(int i=0;i<tempSearch.length();i++){
				if(tempSearch.charAt(i)==current.getChar()){
					if (i==tempSearch.length()-1){
						return true;
					}
					current=current.getLink();
				}
			lastMatchIndex=lastMatchIndex.getLink(); //moves current to next Node after last match
			lengthLeftOver-=1;
			}
		}
		return false;
	}
	public boolean searchIgnoreCase(String key){
		return searchIgnoreCase(key, 0);
	}
	public boolean searchIgnoreCase(String key, int startAt){
		StringLog tempSearch = new StringLog(key); //creates StringLog with given String key
		int lengthLeftOver = length-startAt; //created to make sure not to check for 5 characters if there's only 2 left to check...
		current = getNodeAtIndex(startAt); //sets current to node at index startAt
		CharLLNode lastMatchIndex = current; //creates temp CharLLNode for iteration
		while (lengthLeftOver>=tempSearch.length()){ 
			current = lastMatchIndex;
			for(int i=0;i<tempSearch.length();i++){
				if(tempSearch.getNodeAtIndex(i).getCharToLowerCase()==current.getCharToLowerCase()){
					if (i==tempSearch.length()-1){
						return true;
					}
					current=current.getLink();
				}
			lastMatchIndex=lastMatchIndex.getLink(); //moves current to next Node after last match
			lengthLeftOver-=1;
			}
		}
		return false;
	}	
	//returns char at index - 0 is first character
	//returns error if index does not exist
	@Override
	public char charAt(int index) {
		char selectedChar;
		current = getNodeAtIndex(index);
		selectedChar = current.getChar();
		return selectedChar;
	}
	//Start is inclusive (0 is first character), end is exclusive
	//returns up to last character
	@Override
	public String substring(int start) {
		return substring(start, this.length());
	}
	//Start is inclusive (0 is first character), end is exclusive
	//i.e. 0,4 returns the first, second, third, and fourth character
	@Override
	public String substring(int start, int end) {
		current = getNodeAtIndex(start);
		StringLog returnString = new StringLog(""+current.getChar());
		for(int iterate=(end-start-1); iterate>0 ;iterate--){
			current = current.getLink();
			CharLLNode nextNode = new CharLLNode(current.getChar());
			returnString.getCurrent().setLink(nextNode);
			returnString.setCurrent(nextNode);
		}
		return returnString.toString();
	}
	//inserts the element immediately after the index
	//to insert at beginning enter -1
	@Override
	public void insert(String element, int index) {
		StringLog tempStringLog = new StringLog(element);
		length+=tempStringLog.length();
		while (tempStringLog.getCurrent().getLink()!=null){
			tempStringLog.setCurrent(tempStringLog.getCurrent().getLink());
		}
		if (index==-1){
			tempStringLog.getCurrent().setLink(this.head);
			this.head = tempStringLog.getHead();
		}
		else {
			current = this.getNodeAtIndex(index);
			tempStringLog.getCurrent().setLink(current.getLink());
			current.setLink(tempStringLog.getHead());			
		}
	}
	public void addToEnd(String element){
		current = getNodeAtIndex(this.length()-1);
		StringLog tempStringLog = new StringLog(element);
		length+=tempStringLog.length();
		current.setLink(tempStringLog.getHead());
	}
	public void addToEnd(char element){
		current = getNodeAtIndex(this.length()-1);
		CharLLNode tempNode = new CharLLNode(element);
		current.setLink(tempNode);
		length+=1;
	}
	@Override
	public void clear() {
		head = new CharLLNode();
		current = head;
		length = 1;
	}
	@Override
	public int length() {
		return length;
	}
	public CharLLNode getNodeAtIndex(int index){
		try{
			if (length < index){
			throw new NullPointerException();
			}
		}
		catch (NullPointerException e){
		}
		current = head;
		for(int i=0;i<index;i++){
			current = current.getLink();
		}
		return current;
	}
	public void rEVERSE(){
		CharLLNode newHead = getNodeAtIndex(this.length()-1);
		for(int i=this.length()-2;i>0;i++){
			current = getNodeAtIndex(i);
			current.getLink().setLink(current);
		}
		head.getLink().setLink(head);
		head = newHead;
	}
	public StringLog reverse(StringLog change){
		StringLog temp = new StringLog("0");
		temp.head = getNodeAtIndex(this.length()-1);
		temp.setCurrent(temp.head);
		for(int i=this.length()-2;i>=0;i++){
			temp.getCurrent().setLink(getNodeAtIndex(i));
			temp.setCurrent(temp.getCurrent());
		}
		return temp;
	}
	public CharLLNode getHead(){
		return head;
	}
	public CharLLNode getCurrent(){
		return current;
	}
	public void setCurrent(CharLLNode newNode){
		current = newNode;
	}
	public String toString(){
		String output="";
		current = head;
		while (current.getLink()!=null){
			output=output+current.getChar();
			current = current.getLink();
		}
		output=output+current.getChar();
		return output;
	}
//PRIVATE METHODS
	//Used for constructors
	private void readInStringLog(String data){
		try {
			data.charAt(0);//returns error if string is empty
		}
		catch (Exception e){
			System.out.println("ERROR: Please enter a valid String");
		}
		this.head = new CharLLNode(data.charAt(0));
		this.current = head;
		length = 1;
		if (data.length()>1){
			for (int i=1;i<data.length();i++){
				CharLLNode temp = new CharLLNode(data.charAt(i));
				current.setLink(temp);
				current = temp;
				length += 1;
			}
		}
	}
//	private void readInStringLog(){
//		while(c=keyboard.nextByte()!= SOMETHING){
//			
//		}
//		char x = (char)keyboard.nextByte();
//		this.head = new CharLLNode(data.charAt(0));
//		this.current = head;
//		length = 1;
//		if (data.length()>1){
//			for (int i=1;i<data.length();i++){
//				CharLLNode temp = new CharLLNode(data.charAt(i));
//				current.setLink(temp);
//				current = temp;
//				length += 1;
//			}
//		}
//	}
	public static void main(String arg[]){
	}
	
}