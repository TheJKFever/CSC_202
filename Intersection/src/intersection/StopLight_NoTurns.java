package intersection;

import java.util.Random;

public class StopLight_NoTurns {
	static Random rand = new Random();
	private static float NSwait=0, EWwait=0;
	private static Lane eastbound = new Lane("Eastbound", true);
	private static Lane westbound = new Lane("Westbound", true);	
	private static Lane southbound = new Lane("Southbound", false);	
	private static Lane northbound = new Lane("Northbound", false);		
	
	public StopLight_NoTurns(){
	}
	
	
	public static void simulation(float maxWaitTime, int maxCarLimit){ //runs 1000 cars with varying maxWaitTime and carLimit
		simulate(1000, maxWaitTime, 10, maxCarLimit);
	}
	
	public static void simulation(int iterations, int carLimit){ //Use to test carLimits to find best maxWaitTime
		simulate(iterations, 10000, 10, carLimit);
	}
	
	public static void simulation(int iterations, float maxWaitTime){// use to test maxWaitTime
		simulate(iterations, maxWaitTime, 10, 2);
	}

	public static void simulation(int iterations, int delay, int maxCarLimit){// use to test maxWaitTime
		simulate(iterations, (delay*30), delay, 2);
	}
	
	public static void simulation(float maxWaitTime, int delay, int maxCarLimit){ //runs 1000 cars with varying maxWaitTime and carLimit
		simulate(1000, maxWaitTime, delay, maxCarLimit);
	}
	
	
	//Main Simulations Method
	public static void simulate(int iterations, float maxWaitTime, int delay, int maxCarLimit){
		//adds one car each iteration, and lets one car go through green each way each iteration
		//then checks if queue size is greater or equal to carLimit, if so changes lights.
System.out.println("SIMULATION: "+iterations+" iterations with a max lane car limit of "+maxCarLimit+"\n" +
		"a delay of "+delay+" milliseconds, and a maximum wait time of "+maxWaitTime);
		float maxWaitCount=0;
		Time totalTime = new Time();
		float averageTime=0;
Time stopwatch = new Time();
//Start Stopwatch
System.out.println(stopwatch);		
		for (int i=0;i<iterations;i++){//go through simulation 'iterations' number of times
			addCar();
			
			
//PRINT OUT REPORT (N#,Nwait,W#,Wwait,S#,Swait,E#,Ewait)			
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
			if (eastbound.isGreen()){ //let one car through and change lights if carLimit is reached
				//Let a set of cars go through
				if (!eastbound.getCarQueue().isEmpty()){
					averageTime+=eastbound.getCarQueue().front().getArriveTime().getDuration();
					eastbound.getCarQueue().dequeue();
				}
				if (!westbound.getCarQueue().isEmpty()){
					averageTime+=westbound.getCarQueue().front().getArriveTime().getDuration();
					westbound.getCarQueue().dequeue();
				}
				//change light and set new max
				checkWaitTime(southbound);
				checkWaitTime(northbound);
				if (NSwait>maxWaitCount) maxWaitCount=NSwait;
				//checks if light should change
				if ((southbound.getCarQueue().getLength() >= maxCarLimit)
				|| (northbound.getCarQueue().getLength() >= maxCarLimit) || (NSwait>maxWaitTime)) {
					changeLights();
				}
			} //end east & west 
			else { 
				//Let a set of cars go through
				if (!southbound.getCarQueue().isEmpty()){
					averageTime+=southbound.getCarQueue().front().getArriveTime().getDuration();
					southbound.getCarQueue().dequeue();
				}
				if (!northbound.getCarQueue().isEmpty()){
					averageTime+=northbound.getCarQueue().front().getArriveTime().getDuration();
					northbound.getCarQueue().dequeue();
				}
				//change light and set new max
				checkWaitTime(eastbound);
				checkWaitTime(westbound);
				if (EWwait>maxWaitCount) maxWaitCount=EWwait;
				if ((eastbound.getCarQueue().getLength()>=maxCarLimit)
				|| (westbound.getCarQueue().getLength()>=maxCarLimit) || (NSwait>maxWaitTime)){
						changeLights();
					}
			} // end north and south
			try {
			    Thread.sleep(delay);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}//end iteration Loop
		System.out.println("Average Time: "+(averageTime/iterations));
		System.out.println("MaxWait Time: "+maxWaitCount);
		float totalTimeF=totalTime.getDuration();
		System.out.println("Total Time: "+totalTimeF);
		System.out.println("Average/MaxWait: "+((averageTime/iterations)/(maxWaitCount)+"%"));
		stopwatch.setTime();
// Stop stopwatch
System.out.println(stopwatch);
	}

	private static void addCar(){
		int randomLane = rand.nextInt(4);
		Car newCar = new Car();
		switch(randomLane){ //add a car into a lane
			case 0: //eastbound
				eastbound.getCarQueue().enqueue(newCar);
				break; 
			case 1: //westbound
				westbound.getCarQueue().enqueue(newCar);
				break; 
			case 2: //southbound
				southbound.getCarQueue().enqueue(newCar);
				break;
			case 3: //northbound
				northbound.getCarQueue().enqueue(newCar);
				break;				
		}
		newCar.setArriveTime();
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
	
	private static void checkWaitTime(Lane laneToCheck){
		if (!laneToCheck.getCarQueue().isEmpty()){
			float temp = laneToCheck.getCarQueue().front().getArriveTime().getDuration();
			if (laneToCheck==westbound||laneToCheck==eastbound){
				if (temp>EWwait) EWwait = temp;
			} else {
				if (temp>NSwait) NSwait = temp;
			}
		}
	}

	
	public static void main (String args[]){
		simulate(400, 120, 30, 2);
		
	}
	
}