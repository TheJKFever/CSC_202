public class DoublyLinkedList<T> implements DoublyLinkedListLogInterface<T> {

	Node<T> head, current, previous;
	private int length;
	
	public DoublyLinkedList() {
		head = null;
		current = null;
		previous = null;
		length=0;
	}

	@Override
	// insert at the end
	public void insertBetween(T data) {
		Node<T> newNode = new Node<T>(data);
		// is empty
		if (isEmpty()){
			head=newNode;
		}
		else {
			current = head;
			//add to front
			if (newNode.compareTo(head.getData())<=0){
				newNode.setFront(head);
				head.setBack(newNode);
				head=newNode;
			} 
			else if (head.getFront()==null){
			// only one element and set to end
					newNode.setBack(head);
					head.setFront(newNode);
			}
			else {
				while(current.getFront()!=null) {
					//insert at middle
					current=current.getFront();
					if (newNode.compareTo(current.getData())<=0){
						newNode.setBack(current.getBack());
						newNode.setFront(current);
						current.getBack().setFront(newNode);
						current.setBack(newNode);
						length+=1;
						return;
					}
				}
				//insert at end
				newNode.setBack(current);
				current.setFront(newNode);
			}
		}
		length+=1;
	}
	
	public void insertAtStart(T data) {
		Node<T> newNode = new Node<T>(data);
		if (!isEmpty()){
			newNode.setFront(head);
			head.setBack(newNode);
		}
		head=newNode;		
		length+=1;
	}
	
	public void insertAtEnd(T data) {
		Node<T> newNode = new Node<T>(data);
		//finds last Node and sets back of newNode to that
		if (!isEmpty()){
			current=head;
			while(current!=null){
				if (current.getFront()==null){
					newNode.setBack(current);
					current.setFront(newNode);
					current=current.getFront();					
				}
				current=current.getFront();
			}
		}
		//nothing in list
		else {
			head=newNode;
		}
		length+=1;
	}
	

	@Override
	public void remove(T data) throws DoublyLinkedListLogUnderflowException {
	//note: leaves current on garbage
		if (isEmpty()){
			throw new DoublyLinkedListLogUnderflowException("Remove from an empty list");
		}
		if (contains(data)) {
			// check only single
			if (head.getFront() == null) {
				clear();
				return;
			}
			// remove at head
			else if (current == head) {
				head = current.getFront();
				head.setBack(null);
			} 
			// remove at end
			else if (current.getFront()==null){
				current.getBack().setFront(null);
			} 
			// remove in between
			else {
				current.getBack().setFront(current.getFront());
				current.getFront().setBack(current.getBack());
			}
			length-=1;
		} else {
			throw (new DoublyLinkedListLogUnderflowException(data + " does not exist"));
		}
	}

	@Override
	public boolean contains(T data) {
		boolean found = false;
		current = head;
		if (!isEmpty()) {
			do {
				if (current.getData().equals(data)) {
					found = true;
					break;
				}
				current=current.getFront();
			} while (current != null);
		}
		return found;
	}

	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	@Override
	public void clear() {
		head = null;
		length=0;
	}

	@Override
	public int size() {
		return length;
	}

	public String toString() {
		String output = "";
		current = head;
		if (!isEmpty()) {
			while (current!=null) {
				output += current.getData() + "\n";
				current = current.getFront();
			}
		}
		return output;

	}
}
