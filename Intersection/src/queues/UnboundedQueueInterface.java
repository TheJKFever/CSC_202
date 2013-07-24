package queues;

public interface UnboundedQueueInterface<T> extends QueueInterface<T> {
	void enqueue(T element);
	// Places element at the end of the queue.

}
