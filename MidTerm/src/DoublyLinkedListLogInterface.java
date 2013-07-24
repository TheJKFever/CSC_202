
public interface DoublyLinkedListLogInterface <T> {
	//at the end of the list
	public void insertBetween(T data);
	public void insertAtStart(T data);
	public void insertAtEnd(T data);
	public void remove(T data) throws DoublyLinkedListLogUnderflowException;
	public boolean contains(T data);
	public boolean isEmpty();
	public void clear();
	public int size();
}
