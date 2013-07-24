
public class Demo {

	public static void main(String[] args) {
		System.out.println("TEST: keyboard entry for StringLog sLog");
		System.out.println("Please enter a line for this new StringLog");
		StringLog sLog = new StringLog();
		System.out.println(sLog);

		System.out.println("\nTEST: entry: \"This is my String Log\"");
		sLog = new StringLog("This is my String Log");
		System.out.println(sLog);
		
		System.out.println("TEST: search for word \"my\"");
		System.out.println("If sLog.search(\"my\")==true");
		if(sLog.search("my")==true){
			System.out.println("PASS\n");
		}
		else{
			System.out.println("FAIL");
		}
					
		System.out.println("TEST: search for word \"Log\"");
		System.out.println("If sLog.search(\"Log\")==true");
		if(sLog.search("Log")==true){
			System.out.println("PASS\n");		
		}
		else{
			System.out.println("FAIL");
		}
		
		System.out.println("TEST: search for word \"Elephant\"");
		System.out.println("If sLog.search(\"Elephant\")==false");
		if(sLog.search("Elephant")==false){
			System.out.println("PASS\n");
		}
		else{
			System.out.println("FAIL");
		}
		
		System.out.println("TEST: search for word \"my\" starting at index 0");
		System.out.println("If sLog.search(\"my\", 0)==true");
		if (sLog.search("my", 0)==true){
			System.out.println("PASS\n");
		}
		else{
			System.out.println("FAIL");
		}
		
		System.out.println("TEST: search for word \"my\" starting at index 10");
		System.out.println("If sLog.search(\"my\", 10)==false");
		if (sLog.search("my", 10)==false){
			System.out.println("PASS\n");
		}
		else{
			System.out.println("FAIL");
		}
		
		try{
			System.out.println("TEST: search for word \"my\" starting at index 100");
			System.out.println("If sLog.search(\"my\", 100) throws Exception");
			sLog.search("my", 100);
			System.out.println("FAIL");
		}
		catch (Exception e){
			System.out.println("PASS\n");
		}

		System.out.println("TEST: searchIgnoreCase for word \"string\"");
		System.out.println("If sLog.searchIgnoreCase(\"string\")==true");
		if (sLog.searchIgnoreCase("string")==true){
			System.out.println("PASS\n");
		}
		else{
			System.out.println("FAIL");
		}

		System.out.println("TEST: charAt function");
		System.out.println("If sLog.charAt(0)=='T'");
		if (sLog.charAt(0)=='T'){
			System.out.println("PASS\n");
		}
		else{
			System.out.println("FAIL");
		}
	
		
		System.out.println("TEST: substring(int start)");
		System.out.println("If sLog.substring(3).equals(\"s is my String Log\")");
		if (sLog.substring(3).equals("s is my String Log")){
			System.out.println("PASS\n");
		}
		else{
			System.out.println("FAIL");
		}
		
		System.out.println("TEST: substring(int start, int end)");
		System.out.println("sLog.substring(3,10): "+sLog.substring(3,10)+"\n");
		
		System.out.println("TEST: insert(String element, int index)");
		System.out.println("sLog.insert(\"Super Awesome \", 10): ");
		sLog.insert("Super Awesome ", 10);
		System.out.println(sLog+"\n");

		System.out.println("TEST: addToEnd(String element)");
		System.out.println("sLog.addToEnd(\"! BAM!\"): ");
		sLog.addToEnd("! BAM!");
		System.out.println(sLog+"\n");

		System.out.println("TEST: addToEnd(char element)");
		System.out.println("sLog.addToEnd(\'?'\"): ");
		sLog.addToEnd('?');
		System.out.println(sLog+"\n");
		
		System.out.println("TEST: clear()");
		System.out.println("sLog.clear(): ");
		sLog.clear();
		if (sLog.getHead().getChar()=='\0'){
			System.out.println("PASS\n");
		}
		
		System.out.println("TEST: Number Constructor");
		System.out.println("Number newNum1 = new Number()");		
		Number newNum1 = new Number();
		System.out.println(newNum1+"\n");
		
		System.out.println("TEST: Number Constructor(String parameter)");
		System.out.println("Number newNum2 = new Number(\"5000000100005\")");
		Number newNum2 = new Number("5000000100005");
		System.out.println(newNum2+"\n");
		
		System.out.println("TEST: add function\n" +
				"newNum1.add(newNum2)");
		System.out.println(""+newNum1.add(newNum2)+"\n");
		
		System.out.println("TEST: subtract function\n" +
				"newNum1.subtract(newNum2)");
		System.out.println(""+newNum1.subtract(newNum2)+"\n");
		
		
		
		
	
	}//Main
}//Class