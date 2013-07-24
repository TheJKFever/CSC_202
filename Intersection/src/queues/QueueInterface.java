package queues;

public interface QueueInterface<T> {
	void dequeue() throws QueueUnderflowException;
	// Throws QueueUnderflowException if the queue is empty,
	// otherwise removes the first element from the queue.

	boolean isEmpty();
	// returns true if this queue is empty, otherwise returns false.

}