
public interface StringLogInterface {

	//start at the beginning of the StringLog, returns true if contains key
	public boolean search(String key);
	
	//startAt is inclusive - 0 starts from the beginning of the string
	//returns true if contains key
	public boolean search(String key, int startAt);
	
	//returns char at index - 0 is first character
	public char charAt(int index);
	
	//Start is inclusive (0 is first character), end is exclusive
	//returns up to last character
	public String substring(int start);
	
	//Start is inclusive (0 is first character), end is exclusive
	//i.e. 0,4 returns the first, second, third, and fourth character
	//if the number of characters does not exist, returns up to last character
	public String substring(int start, int end);
	
	//inserts the element immediately after the index
	//to insert at beginning enter -1
	//if int > StringLog length enter at end
	public void insert(String element, int index);
		
	public void clear();
	
	public int length();
	
	public String toString();
	
}