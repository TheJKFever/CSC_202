
public class DLLDriver {

	public static void main(String[] args) throws DoublyLinkedListLogUnderflowException{
		DoublyLinkedList <String> aDLL = new DoublyLinkedList <String> () ;
		aDLL.insertBetween("ANT");
		System.out.println(aDLL);
		aDLL.insertBetween("CAT");
		System.out.println(aDLL);
		aDLL.insertBetween("BEE");
		System.out.println(aDLL);
		
		aDLL.remove("ANT");
		System.out.println(aDLL);
		aDLL.remove("CAT");		
		System.out.println(aDLL);
		aDLL.insertAtEnd("Ardvark");
		System.out.println(aDLL);
		aDLL.insertAtStart("Whale");
		System.out.println(aDLL);
		System.out.println(aDLL.contains("Whale"));
		

		aDLL.clear();
		System.out.println(aDLL.isEmpty());
		System.out.println(aDLL);

		DoublyLinkedList <Integer> numDLL = new DoublyLinkedList <Integer> () ;
		numDLL.insertBetween(1);
		System.out.println(numDLL);
		numDLL.insertBetween(3);
		System.out.println(numDLL);
		numDLL.insertBetween(2);
		System.out.println(numDLL);
		
		numDLL.remove(1);
		System.out.println(numDLL);
		
	}

}
