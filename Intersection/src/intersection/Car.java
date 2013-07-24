package intersection;

public class Car {
	private String name;
	private Time arriveTime;

	//Constructor
	public Car(){
		arriveTime = null;
		this.name = "Car";
	}
	
	public Car(String name){
		arriveTime = null;
		this.name = name;
	}
	
	//GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Time getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime() {
		arriveTime = new Time();
		this.arriveTime.setTime();
	}

}
