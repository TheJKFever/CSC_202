public class Number {
	
	private StringLog sLog;
	
	public Number(){
		sLog = new StringLog();
		validate();
	}
	public Number(String number){
		sLog = new StringLog(number);
		validate();	
	}
	
	public Number add(Number addThis){
		int one = sLog.length()-1;
		int two = addThis.getsLog().length()-1;
		int addOne, addTwo, carryOver=0;
		Number tempNum = new Number("0");
		while ((one>=0)||(two>=0)){
			tempNum.getsLog().setCurrent(tempNum.getsLog().getHead());
			addOne = toInt(sLog.charAt(one));
			addTwo = toInt(addThis.getsLog().charAt(two));
			if (one<0){ addOne=0; }
			if (two<0){	addTwo=0; }
			int sum = addOne+addTwo+carryOver;
			if (sum>=10){
				carryOver=1;
				tempNum.getsLog().getCurrent().setChar(toChar((sum-10)));
			} else {
				tempNum.getsLog().getCurrent().setChar(toChar(sum));
				carryOver = 0;
			}
			one-=1;
			two-=1;
			if((one>=0)||(two>=0)){
				tempNum.getsLog().insert("0", -1);
			}
		}
		return tempNum;
	}
	public Number subtract(Number subThis){
		boolean negative=false;
		StringLog subFrom, subThisL;
		//Checks which StringLog is a higher value and sets it to subFrom
		if ((sLog.length()<subThis.getsLog().length())||((sLog.length()==subThis.getsLog().length())&&(toInt(sLog.getHead().getChar())<toInt(subThis.getsLog().getHead().getChar())))){
			negative = true;
			subFrom = subThis.getsLog();
			subThisL = sLog;
		} else {
			subFrom = sLog;
			subThisL = subThis.getsLog();
		}
		int top = subFrom.length()-1;
		int bottom = subThisL.length()-1;
		int subTop, subBottom, carryOver=0;
		Number tempNum = new Number("0");
		while (top>=0){
			tempNum.getsLog().setCurrent(tempNum.getsLog().getHead());
			subTop = toInt(subFrom.charAt(top));
			subBottom = toInt(subThisL.charAt(bottom));
			if (bottom<0){	subBottom=0; }
			int result = subTop-subBottom-carryOver;
			if (result<0){
				carryOver=1;
				tempNum.getsLog().getCurrent().setChar(toChar((10+result)));
			} else {
				tempNum.getsLog().getCurrent().setChar(toChar(result));
				carryOver = 0;
			}
			top-=1;
			bottom-=1;
			if(top>=0){
				tempNum.getsLog().insert("0", -1);
			}
		}
		if (negative){ tempNum.getsLog().insert("-", -1); }
		return tempNum;
	}
	public StringLog getsLog() {
		return sLog;
	}
	public void setsLog(StringLog sLog) {
		this.sLog = sLog;
	}
	public String toString(){
		return sLog.toString();
	}
	//PRIVATE METHODS
	private int toInt(char c){
		return ((int)c)-48;
	}
	private char toChar(int x){
		return ((char)(x+48));
	}
	private boolean isNumber(StringLog element){
		element.setCurrent(element.getHead());
		while(element.getCurrent()!=null){
			try{
				int x=(int)(element.getCurrent().getChar());
				if ((x<48)||(x>57)){
					throw new Exception("Invalid Input");
				}
			}
			catch(Exception e){
				return false;
			}
			element.setCurrent(element.getCurrent().getLink());
		}
		return true;
	}
	private void validate(){
		if(isNumber(sLog)==false){
			System.out.println("You did not enter a valid number, please enter numbers only.");
			sLog = new StringLog();
			validate();
		}
	}
	
	public static void main(String args[]){
	}
}
