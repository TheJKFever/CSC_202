
public class DCLLDemo {

	public static void main(String[] args) throws DCLLException{
		// TODO Auto-generated method stub
		DCLL <String> aDCLL = new DCLL <String> () ;
		aDCLL.insert("ANT");
		aDCLL.insert("BEE");
		aDCLL.insert("CAT");
		System.out.println(aDCLL);
		aDCLL.remove("ANT");
		aDCLL.remove("BEE");
		aDCLL.remove("CAT");
		
		System.out.println(aDCLL);
/*		
		System.out.println(aDCLL.contains("ANT"));
		System.out.println(aDCLL.contains("CAT"));
		System.out.println(aDCLL.contains("CA"));*/

	}

}
