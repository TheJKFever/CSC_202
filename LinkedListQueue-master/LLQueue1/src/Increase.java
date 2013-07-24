import java.util.Random;


public class Increase implements Runnable {
	private Random rand = new Random();
	private Car aCar;
	private CLLQueue <Car> aQueue;
	private int amount;
	/**
	 * @param aQueue
	 * @param amount
	 */
	public Increase(CLLQueue <Car> aQueue, int amount) {
		this.aQueue = aQueue;
		this.amount = amount;
	}
	@Override
	public void run() {
		try {
		    Thread.sleep(rand.nextInt(50)+50);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		for(int i=0; i<amount; i++) {
			aCar = new Car(i,System.currentTimeMillis());
			try {
			    Thread.sleep(rand.nextInt(50)+50);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			this.aQueue.enqueue(aCar);
		}
		// TODO Auto-generated method stub
		
	}

}
