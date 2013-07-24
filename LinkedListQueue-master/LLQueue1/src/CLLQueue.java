public class CLLQueue<T> implements LLQueueInterface<T> {

	private Node<T> rear, current;
	private int size;

	public CLLQueue() {
		rear = null;
	}

	public synchronized T getFront()  throws QueueUnderFlowException {
		if (!isEmpty()) {
			return rear.getLink().getData();			
		} else {
			throw new QueueUnderFlowException("Empty Queue!");
		}

	}

	@Override
	public synchronized void enqueue(T data) {
		// TODO Auto-generated method stub
		// an empty queue
		Node<T> newNode = new Node<T>(data);
		if (isEmpty()) {
			rear = newNode;
			newNode.setLink(rear);
		} else {
			// not empty queue / enqueue to the rear
			newNode.setLink(rear.getLink());
			rear.setLink(newNode);
			rear = newNode;
		}
		size++;
	}

	@Override
	public synchronized T dequeue() throws QueueUnderFlowException {
		// TODO Auto-generated method stub
		// only one node left
		T element = null;
		if (!isEmpty()) {
			element = rear.getData();
			if (rear.getLink() == rear) {
				rear = null;
			} else {
				rear.setLink(rear.getLink().getLink());
			}
			size--;
		} else {
			throw new QueueUnderFlowException("Empty Queue!");
		}

		return element;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (rear == null);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String toString() {
		String list = "";
		try {
			current = rear.getLink();
		} catch (NullPointerException e) {
			return list;
		}
		do {
			list += current.getData() + " ";
			current = current.getLink();
		} while (current != rear.getLink());

		return list;
	}
}
