
public interface DCLLInterface <T> {
	//at the end of the list
	public void insert(T data);
	public void remove(T data) throws DCLLException;
	public boolean contains(T data);
	public boolean isEmpty();
	public void clear();
	public int size();
}
