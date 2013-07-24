import java.util.Random;

public class TrafficControlDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car nCar = null;
		Car sCar = null;
		Car wCar = null;
		Car eCar = null;
		Random rand = new Random();
		CLLQueue<Car> qN = new CLLQueue<Car>(); //create lane
		CLLQueue<Car> qS = new CLLQueue<Car>(); //create lane
		CLLQueue<Car> qE = new CLLQueue<Car>(); //create lane
		CLLQueue<Car> qW = new CLLQueue<Car>(); //create lane
		Runnable r1 = new Increase(qN, rand.nextInt(10));
		Thread t1 = new Thread(r1);
		Runnable r2 = new Increase(qE, rand.nextInt(10));
		Thread t2 = new Thread(r2);
		Runnable r3 = new Increase(qS, rand.nextInt(10));
		Thread t3 = new Thread(r3);
		Runnable r4 = new Increase(qW, rand.nextInt(10));
		Thread t4 = new Thread(r4);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
	
		}
		// making sure that not getting from before enqueue
		//while ((t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive())||(!qN.isEmpty())||(!qE.isEmpty())||(!qW.isEmpty())||(!qS.isEmpty())) {
			try {
				nCar = qN.getFront();
				nCar.setWaitTime(System.currentTimeMillis());
			} catch (QueueUnderFlowException e) {
				// TODO Auto-generated catch block e.printStackTrace();
				e.getMessage();
			}
			try {
				sCar = qS.getFront();
				sCar.setWaitTime(System.currentTimeMillis());
			} catch (QueueUnderFlowException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			try {
				wCar = qW.getFront();
				wCar.setWaitTime(System.currentTimeMillis());
			} catch (QueueUnderFlowException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			try {
				eCar = qE.getFront();
				eCar.setWaitTime(System.currentTimeMillis());
			} catch (QueueUnderFlowException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}

			// Largest waiting time to dequeue
			// by sorting and searching for the max
				// handle the cars who have arrived to the stop sign at the same time
			
		//}

	


		
		// aCar.setWaitTime(System.currentTimeMillis());
		System.out.println("North: \n" + qN);
		System.out.println("South:\n " + qS);
		System.out.println("East: \n" + qE);
		System.out.println("West: \n" + qW);
		// System.out.println(qN);

	}
}
