public class LLQueue<T> implements LLQueueInterface<T> {
	Node<T> front, rear, current;

	public LLQueue() {
		front = null;
		rear = null;
		current = null;
	}

	@Override
	public void enqueue(T data) {
		// TODO Auto-generated method stub
		// empty queue
		Node<T> newNode = new Node<T>(data);
		if (isEmpty()) {
			front = newNode;
		} else {
			// not empty queue
			rear.setLink(newNode);
		}
		rear = newNode;
	}

	@Override
	public T dequeue() throws QueueUnderFlowException {
		// TODO Auto-generated method stub
		if (!isEmpty()) {
			T element = front.getData();
			front = front.getLink();
			if(front==null) {
				rear = null;
			}
			return element;
		} else {
			throw new QueueUnderFlowException("Empty Queue");
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (front == null);
	}

	public String toString() {
		String list = "";
		current = front;
		while (current != null) {
			list += current.getData() + " ";
			current = current.getLink();
		}
		return list;
	}

}
