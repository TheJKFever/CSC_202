package intersection;

import queues.LinkedQueue;

public class Lane {
	private String streetName;
	private boolean green, red;
	private LinkedQueue<Car> carQueue;

	public Lane(String streetName, boolean go){
		carQueue = new LinkedQueue<Car>();
		this.setStreetName(streetName);
		green=go;
		red=!go;
	}
	
	public boolean isGreen() {
		return green;
	}

	public void setGreen() {
		this.green = true;
		this.red = false;
	}

	public boolean isRed() {
		return red;
	}

	public void setRed() {
		this.red = true;
		this.green = false;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public LinkedQueue<Car> getCarQueue() {
		return carQueue;
	}
	
	public int getNumCars (){
		return carQueue.getLength();
	}

}
