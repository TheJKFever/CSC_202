package queues;

public class LinkedQueue<T> implements UnboundedQueueInterface<T> {
	private LLNode<T> front, rear, current;
	int length;
	
	public int getLength() {
		return length;
	}

	public LinkedQueue(){
		front = null;
		rear = null;
		length = 0;
	}
		
	@Override
	public void dequeue() {
		//Empty Case
		if (isEmpty()){
			throw new QueueUnderflowException("Error - dequeue an empty queue");
		}
		//More than 1 case
		else {
			front = front.getLink();
		}
		length-=1;
	}
	@Override
	public boolean isEmpty() {
		if (front==null){
			return true;
		}
		return false;
	}

	@Override
	public void enqueue(Object element) {
		@SuppressWarnings("unchecked")
		LLNode<T> newNode = new LLNode<T>((T)element);
		//Empty Case
		if (isEmpty()){
			front = newNode;
			rear = newNode;
		}
		//More than 1 case
		else {
			rear.setLink(newNode);
			rear=newNode;
		}
		length+=1;
	}
	
	public T front(){
		return front.getInfo();
	}

	public String toString(){
		String queue = "";
		//Empty Case
		if (length>0){
			current=front;
			queue+=current;
			for (int i=1;i<length;i++){
				queue+=current.toString()+", ";
			}
		}		
		return queue;
	}	
}
