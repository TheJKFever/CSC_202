package intersection;
import java.util.Random;

public class StopLight_NoTurns {
	private static Random rand = new Random();
	private static float NSwait=0, EWwait=0;
	private static Lane eastbound = new Lane("Eastbound", true);
	private static Lane westbound = new Lane("Westbound", true);	
	private static Lane southbound = new Lane("Southbound", false);	
	private static Lane northbound = new Lane("Northbound", false);	

	private static float maxWaitCount=0;
	private static Time totalTime = new Time();
	private static float averageTime=0;			
	
	public static void simulation(float maxWaitTime, int maxCarLimit){ //runs 1000 cars with varying maxWaitTime and carLimit
		simulate(1000, maxWaitTime, 10, maxCarLimit);
	}
	public static void simulation(int numberOfCars, int carLimit){ //Use to test carLimits to find best maxWaitTime
		simulate(numberOfCars, 10000, 10, carLimit);
	}
	public static void simulation(int numberOfCars, float maxWaitTime){// use to test maxWaitTime
		simulate(numberOfCars, maxWaitTime, 10, 2);
	}
	public static void simulation(int numberOfCars, int delay, int maxCarLimit){// use to test maxWaitTime
		simulate(numberOfCars, (delay*30), delay, 2);
	}
	public static void simulation(float maxWaitTime, int delay, int maxCarLimit){ //runs 1000 cars with varying maxWaitTime and carLimit
		simulate(1000, maxWaitTime, delay, maxCarLimit);
	}
	
	//Main Simulations Method
	public static void simulate(int numberOfCars, float maxWaitTime, int delay, int maxCarLimit){
		
System.out.println("SIMULATION: "+numberOfCars+" cars with a max lane car limit of "+maxCarLimit+" a delay\n" +
		"of "+delay+" milliseconds and a maximum wait time of "+maxWaitTime);
		
		//Create and start threads to add cars randomly
		Increase increaseEast = new Increase(eastbound, rand.nextInt(numberOfCars), delay);
		Increase inreastWest = new Increase(westbound, rand.nextInt(numberOfCars), delay);
		Increase inreastSouth = new Increase(southbound, rand.nextInt(numberOfCars), delay);
		Increase inreastNorth = new Increase(northbound, rand.nextInt(numberOfCars), delay);		
		
		//While the threads are still running, check every (delay/2) milliseconds
		while ((increaseEast.thread.isAlive())||(inreastWest.thread.isAlive())||(inreastSouth.thread.isAlive())||(inreastNorth.thread.isAlive())) {

//***********************PRINT OUT REPORT********************************
// Format: N#,Nwait,W#,Wwait,S#,Swait,E#,Ewait			
System.out.print(northbound.getNumCars()+",");
if (!northbound.getCarQueue().isEmpty()){
	System.out.print(+northbound.getCarQueue().front().getArriveTime().getDuration()+",");
} else {
	System.out.print(0+",");
}
System.out.print(westbound.getNumCars()+",");
if (!westbound.getCarQueue().isEmpty()){
	System.out.print(+westbound.getCarQueue().front().getArriveTime().getDuration()+",");
} else {
	System.out.print(0+",");
}
System.out.print(southbound.getNumCars()+",");
if (!southbound.getCarQueue().isEmpty()){
	System.out.print(+southbound.getCarQueue().front().getArriveTime().getDuration()+",");
} else {
	System.out.print(0+",");
}
System.out.print(eastbound.getNumCars()+",");
if (!eastbound.getCarQueue().isEmpty()){
	System.out.println(+eastbound.getCarQueue().front().getArriveTime().getDuration());
} else {
	System.out.println(0);
} 
//***********************END REPORT********************************
	
			if (eastbound.isGreen()){ //let one car through and change lights if carLimit is reached
				//Let a set of cars go through
				if (!eastbound.getCarQueue().isEmpty()){
					averageTime+=eastbound.getCarQueue().front().getArriveTime().getDuration();
					getEWWaitTime();
					if (EWwait>maxWaitCount) maxWaitCount=EWwait;
					eastbound.getCarQueue().dequeue();
				}
				if (!westbound.getCarQueue().isEmpty()){
					averageTime+=westbound.getCarQueue().front().getArriveTime().getDuration();
					getEWWaitTime();
					if (EWwait>maxWaitCount) maxWaitCount=EWwait;
					westbound.getCarQueue().dequeue();
				}
				//change light and set new max

				//checks if light should change
				getNSWaitTime();
				if ((southbound.getCarQueue().getLength() >= maxCarLimit)
				|| (northbound.getCarQueue().getLength() >= maxCarLimit) || (NSwait>maxWaitTime)) {
					changeLights();
				}
			} //end east & west 
			else { 
				//Let a set of cars go through
				if (!southbound.getCarQueue().isEmpty()){
					averageTime+=southbound.getCarQueue().front().getArriveTime().getDuration();
					getNSWaitTime();
					if (NSwait>maxWaitCount) maxWaitCount=NSwait;
					southbound.getCarQueue().dequeue();
				}
				if (!northbound.getCarQueue().isEmpty()){
					averageTime+=northbound.getCarQueue().front().getArriveTime().getDuration();
					getNSWaitTime();
					if (NSwait>maxWaitCount) maxWaitCount=NSwait;
					northbound.getCarQueue().dequeue();
				}

				//change light and set new max
				getEWWaitTime();
				if ((eastbound.getCarQueue().getLength()>=maxCarLimit)
				|| (westbound.getCarQueue().getLength()>=maxCarLimit) || (NSwait>maxWaitTime)){
						changeLights();
					}
			} // end north and south
			try {
			    Thread.sleep(delay/2);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		
		}
		int totalCars = (increaseEast.getCarCount()+inreastWest.getCarCount()+inreastSouth.getCarCount()+inreastNorth.getCarCount());
		System.out.println("Total num of cars: "+totalCars);
		System.out.println("Average Time: "+(averageTime/totalCars)/1000+" seconds");
		System.out.println("MaxWait Time: "+maxWaitCount/1000+" seconds");
		float totalTimeF=totalTime.getDuration();
		System.out.println("Total Time: "+totalTimeF/1000+" seconds");
		System.out.println("Average/MaxWait: "+((averageTime/numberOfCars)/(maxWaitCount)+"%"));
	}

	private static void changeLights(){
		if (eastbound.isGreen()){
			eastbound.setRed();
			westbound.setRed();
			northbound.setGreen();
			southbound.setGreen();
			EWwait=0;
		} else {
			northbound.setRed();
			southbound.setRed();
			eastbound.setGreen();
			westbound.setGreen();
			NSwait=0;
		}
	}
	
	private static void getNSWaitTime(){
		float northTime=0;
		float southTime=0;
		if (!northbound.getCarQueue().isEmpty()){
			northTime = northbound.getCarQueue().front().getArriveTime().getDuration();
		}
		if (!southbound.getCarQueue().isEmpty()){
			southTime = southbound.getCarQueue().front().getArriveTime().getDuration();
		}
		if (northTime>southTime) NSwait = northTime;
		else NSwait = southTime;
	}
	private static void getEWWaitTime(){
		float westTime=0;
		float eastTime=0;
		if (!westbound.getCarQueue().isEmpty()){
			westTime = westbound.getCarQueue().front().getArriveTime().getDuration();
		}
		if (!eastbound.getCarQueue().isEmpty()){
			eastTime = eastbound.getCarQueue().front().getArriveTime().getDuration();
		}
		if (westTime>eastTime) EWwait = westTime;
		else NSwait = eastTime;
	}
	
	
	public static void main (String args[]){

		simulate(50000, 120, 2, 2);
		
	}
	
}