
public class LLQueueDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer values[] = {4,3,1,2};
		SelectionSort <Integer>aSort = new SelectionSort<Integer>(values,values.length);
		for(Integer x:values) 
		System.out.print(x + " ");
		aSort.selectionSort();
		System.out.println();
		for(Integer x:values) 
		System.out.print(x + " ");
		System.out.println();
		
		String values2[] = {"BEE","CAT","ANT","A"};
		SelectionSort <String>bSort = new SelectionSort<String>(values2,values2.length);
		for(String x:values2) 
		System.out.print(x + " ");
		bSort.selectionSort();
		System.out.println();
		for(String x:values2) 
		System.out.print(x + " ");
		
/*		LLQueue <String>aQ = new LLQueue<String>();
		try {
			aQ.dequeue();
		} catch  (QueueUnderFlowException e) {
			System.out.println(e.getMessage());
		}
		aQ.enqueue("Ant");
		aQ.enqueue("Bee");
		aQ.enqueue("Cat");
		try {
			aQ.dequeue();
		} catch  (QueueUnderFlowException e) {
			e.getMessage();
		}
		System.out.println(aQ);
		
		LLQueue <Integer>bQ = new LLQueue<Integer>();
		bQ.enqueue(1);
		bQ.enqueue(2);
		bQ.enqueue(3);
		
		System.out.println(bQ);*/
		
	}

}
