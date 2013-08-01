package intersection;
import java.util.Random;

public class Increase implements Runnable {
	private Random rand = new Random();
	private Lane lane; //LinkedQueue <Car> aQueue;
	private int amount;
	private int carCount=0;
	private int delay;
	Thread thread;
	
	public Increase(Lane lane, int amount, int delay) {
		this.lane = lane;
		this.amount = amount;
		this.delay = delay;
		thread = new Thread(this, lane.getStreetName());
		thread.start();
	}
	
	public int getCarCount() {
		return carCount;
	}
	
	@Override
	public void run() {
		try {
		    Thread.sleep(rand.nextInt(delay)+delay);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		for(int i=0; i<amount; i++) {
			Car aCar = new Car();
			aCar.setArriveTime();
			try {
			    Thread.sleep(rand.nextInt(delay)+delay);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			this.lane.getCarQueue().enqueue(aCar);
			carCount++;
		}
	}

}
