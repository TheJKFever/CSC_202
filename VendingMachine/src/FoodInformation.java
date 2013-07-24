import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodInformation {

	
	//Scanner & File
    public static String foodFile = "Food Items Information.txt";
    public static Scanner inputStream = null;

    //Instance Variables
    private int calories, sugar;
    private String foodName, type, btnImgUrl;
    private double fat, price;
    public static ArrayList<FoodInformation> allFoodItems = new ArrayList<FoodInformation>();    
    
    FoodInformation(){
    	foodName="default";
    	calories=0;
    	sugar=0;
    	price=0;
    	fat=0;
    	type="default";
    	btnImgUrl="default";
    }
    
    //read all food items
    public static void readFoodItems(){
	    try
	    {   inputStream = new Scanner(new File(foodFile)); 
	    }
	    catch(FileNotFoundException e)
	    {   System.out.println("Error opening the file " + foodFile);
	        System.exit(0); 
	    }
	    while (inputStream.hasNextLine()){
	    	//Create a new food item and read in all the variables
	    	FoodInformation foodItem = new FoodInformation();
			foodItem.setName(inputStream.nextLine());
			foodItem.setCal(Integer.parseInt(inputStream.nextLine()));
			foodItem.setSugar(Integer.parseInt(inputStream.nextLine()));
			foodItem.setFat(Double.parseDouble(inputStream.nextLine()));
			foodItem.setType(inputStream.nextLine());
			foodItem.setPrice(Double.parseDouble(inputStream.nextLine()));
			foodItem.setImgURL(inputStream.nextLine());
			allFoodItems.add(foodItem);
	    }
	    inputStream.close();
	}

	public static void printAllFoodItems(){
		for(int i=0;i<allFoodItems.size();i++){
		System.out.println(allFoodItems.get(i));
		}    
	}
    
    
    //SETTERS
    public void setName(String name){
    	foodName = name;
    }
    public void setCal(int cal){
    	calories = cal;
    }
    public void setSugar(int newSug){
    	sugar = newSug;
    }
    public void setFat(Double newFat){
    	fat = newFat;
    }
    public void setType(String newType){
    	type = newType;
    }
    public void setPrice(double newPrice){
    	price = newPrice;
    }
    public void setImgURL(String ImgFile){
    	btnImgUrl = ImgFile;
    }

    //GETTERS
    public String getName(){
    	return foodName;
    }
    public int getCal(){
    	return calories;
    }
    public int getSugar(){
    	return sugar;
    }
    public Double getFat(){
    	return fat;
    }
    public String getType(){
    	return type;
    }
    public double getPrice(){
    	return price;
    }
    public String getImgUrl(){
    	return btnImgUrl;
    }
    
    
    public String toString(){
    	String output = "Food: "+getName()+"\n"+
        	"Type: "+getType()+"\n"+
    		"Calories: "+getCal()+"\n"+
    		"Sugar: "+getSugar()+"\n"+
    		"Fat: "+getFat()+"\n"+
    		"Price: $"+getPrice();
    	return output;
    }
    
    public boolean equals(FoodInformation test1){
    	if ((foodName.equals(test1.foodName))&&(calories==test1.calories)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    
	public static void main(String[] args){
		readFoodItems();
		printAllFoodItems();
    }
}
