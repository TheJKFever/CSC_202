package queues;

public interface UnboundedQueueInterface<T> {
	void enqueue(T element);
	// Places element at the end of the queue.

	void dequeue() throws QueueUnderflowException;
	// Throws QueueUnderflowException if the queue is empty,
	// otherwise removes the first element from the queue.

	boolean isEmpty();
	// returns true if this queue is empty, otherwise returns false.

}
