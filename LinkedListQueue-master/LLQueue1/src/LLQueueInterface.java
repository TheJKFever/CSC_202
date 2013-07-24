
public interface LLQueueInterface <T> {
	public void enqueue(T data);
	public T dequeue() throws QueueUnderFlowException;
	public boolean isEmpty();
}
