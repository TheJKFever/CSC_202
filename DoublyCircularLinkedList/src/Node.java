
public class Node <T> {
	T data;
	Node <T> front; 
	Node <T> back;
	
	 /**
	 * @param data
	 * @param front
	 * @param back
	 */
	public Node(T data) {
		this.data = data;
		this.front = null;
		this.back = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getFront() {
		return front;
	}

	public void setFront(Node<T> front) {
		this.front = front;
	}

	public Node<T> getBack() {
		return back;
	}

	public void setBack(Node<T> back) {
		this.back = back;
	}
	
	
	
}
