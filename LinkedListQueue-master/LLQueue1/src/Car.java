public class Car {
	private int carID;
	private long arrivalTime;
	private long waitTime;
	
	/**
	 * @param carID
	 * @param arrivalTime
	 * @param waitTime
	 */
	public Car(int carID, long arrivalTime) {
		this.carID = carID;
		this.arrivalTime = arrivalTime;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(long currentTime) {
		this.waitTime =  currentTime - this.arrivalTime;
	}

	@Override
	public String toString() {
		return "Car [carID=" + carID + ", arrivalTime=" + arrivalTime
				+ ", waitTime=" + waitTime + "]" +"\n";
	}

	
	
}
