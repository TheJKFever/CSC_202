

public class Dispenser {
	/**
	 * 
	 */

	private int quantityStart, quantityEnd;
	private FoodInformation foodItem;	
	
	public Dispenser(){
		quantityStart=20;
		quantityEnd=20;
		FoodInformation.readFoodItems();
		foodItem = new FoodInformation();
	}
	
	public Dispenser(int foodSelection, int quantity){
		quantityStart=quantity;
		quantityEnd=quantity;
		FoodInformation.readFoodItems();
		foodItem = FoodInformation.allFoodItems.get(foodSelection);		
	}
	
	public void decQuantity(){
		quantityEnd--;
	}
	
	public void restock(){
		quantityStart+=(20-quantityEnd);
		quantityEnd=20;
	}
	
	//SETTERS
	public void setQuantity(int newQuantity){
		quantityStart=newQuantity;
		quantityEnd=newQuantity;
	}
	
	//GETTERS
	public int getQuantity(){
		return quantityEnd;
	}
	public int getDaysSales(){
		return (quantityStart-quantityEnd);
	}
	
	public FoodInformation getFood(){
		return foodItem;
	}
	
	public String toString(){
		String output = foodItem.toString();		
		if (quantityEnd==0){
			output = output+"OUT OF STOCK!";
		}
		else{
			output = output+"Quantity: "+getQuantity();
		}
		return output;
	}
	
	

}
