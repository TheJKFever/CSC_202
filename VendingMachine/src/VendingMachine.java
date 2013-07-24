import java.lang.Math;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
public class VendingMachine {

	
	Date d = new Date();
	String dateFileName = d.toString().replace(':', '_');
	Scanner keyboard = new Scanner(System.in);
	PrintWriter outputToFile = null;
	Scanner inputStream = null;
	public static Scanner staticInputStream = null;
	
	private double cashInserted;
	private Dispenser[] dispenser;
	private double totalChangeInMachine, daysSales = 0;	
	private String location;
	private boolean off;
	
	public VendingMachine(){
		System.out.println("Please give a name for the location of this vending machine: ");
		location = keyboard.nextLine();
		cashInserted = 0;
		totalChangeInMachine = 0;
		daysSales =  0;
		off = false;
		dispenser = createRandomDispensers(10, 20);
	}

	public VendingMachine(int zero){ //int zero is pointless, I just wanted the other constructor to be the default
		cashInserted = 0;
		totalChangeInMachine = 0;
		daysSales = 0;
		off = false;
		location="default";
	}
	
	public VendingMachine(String newLocation){ //initialize 10 dispensers with different items chosen at random with 20 in each
		cashInserted = 0;
		totalChangeInMachine = 0;
		daysSales =  0;
		off = false;
		location = newLocation;
		dispenser = createRandomDispensers(10, 20);//create ten random dispensers with a stock of 20 each
	}

	public void addChange(){
		System.out.println("How much change would you like to add?");
		cashInserted = Double.parseDouble(keyboard.nextLine());
	}
	
	//Check if out of stock
	public boolean outOfStock(){
		int totalStock=0;
		for (Dispenser d: this.getDispenser()){
			totalStock+=d.getQuantity();
		}
		if (totalStock==0){
			return true;
		}
		return false;
	}

	//restock to full capacity
	public VendingMachine reStock(){
			this.dispenser = createRandomDispensers(10, 20);
		return this;
	}
	
	public void addChange(Double changeToAdd){
		cashInserted+=changeToAdd;
	}
	
	public void showOptions(){
		for (int i=0;i<10;i++){
			System.out.println(""+(i+1)+"."+dispenser[i]);
		}
	}
	
	public void makeSale(){
		System.out.println("Enter the number corresponding to the item you want to purchase?");
		Dispenser item = dispenser[Integer.parseInt(keyboard.nextLine())-1];
		makeSale(item);
	}
	
	public void makeSale(Dispenser itemChosen){
		try { 
			if(itemChosen.getQuantity()==0){
				throw new Exception ("OUT OF STOCK");
			}
			else if (cashInserted<itemChosen.getFood().getPrice()){
				throw new Exception ("Error: Please enter more money to purchase this item.\n"+
					"You entered: $"+cashInserted+", this item costs $"+itemChosen.getFood().getPrice()+"\n"+
					"Try Again");
			}
			else {
				recordSale(itemChosen);
				printReceipt(itemChosen);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//SETTERS
	public void setCashInserted(double newCash){
		cashInserted=newCash;
	}
	public void setTotalChangeInMachine(double newTotal){
		totalChangeInMachine = newTotal;
	}
	public void setDaysSales(double newDaysSales){
		daysSales = newDaysSales;
	}
	public void setLocation(String newLocation){
		location = newLocation;
	}

	//GETTERS
	public Dispenser[] getDispenser(){
		return dispenser;
	}
	public double getCashInserted(){
		return cashInserted;
	}
	public double getTotalChangeInMachine(){
		return totalChangeInMachine;
	}
	public double getDaysSales(){
		return daysSales;
	}
	public String getLocation(){
		return location;
	}
	public boolean getOff(){
		return off;
	}
	
	//OPEN/CLOSE BUSINESS
	public void turnOff(){
		closeMachine();
		off=true;
	}
	
	public void closeMachine(){
		printDaysBusiness();
		saveEndOfBusiness();
	}
	
	public static VendingMachine restore(String restoreFile){
		//create a specific vending machine from a file
		File restore = new File(restoreFile);
		try{
			staticInputStream = new Scanner(restore);
		}
		catch (Exception e){
			System.out.println("Could not open restore file");
		}
		VendingMachine vM = new VendingMachine(0);
		vM.cashInserted=Double.parseDouble(staticInputStream.nextLine());
		vM.totalChangeInMachine=Double.parseDouble(staticInputStream.nextLine());
		vM.location=staticInputStream.nextLine();
		vM.dispenser = new Dispenser[10];
		for (int i=0;i<10;i++){
			vM.dispenser[i] = new Dispenser();
			vM.dispenser[i].setQuantity(Integer.parseInt(staticInputStream.nextLine()));
			vM.dispenser[i].getFood().setName(staticInputStream.nextLine());
			vM.dispenser[i].getFood().setCal(Integer.parseInt(staticInputStream.nextLine()));
			vM.dispenser[i].getFood().setPrice(Double.parseDouble(staticInputStream.nextLine()));
			vM.dispenser[i].getFood().setSugar(Integer.parseInt(staticInputStream.nextLine()));
			vM.dispenser[i].getFood().setType(staticInputStream.nextLine());
			vM.dispenser[i].getFood().setFat(Double.parseDouble(staticInputStream.nextLine()));
			vM.dispenser[i].getFood().setImgURL(staticInputStream.nextLine());
		}
		vM.off=false;
		staticInputStream.close();
		return vM;
	}
	
	private void saveEndOfBusiness() {
		//store all vending machines info into a file for next day.
		String locationFileName = "VendingMachine_"+location+".txt";
		try {
			outputToFile = new PrintWriter(locationFileName);
	    }
	    catch (FileNotFoundException e) {
	        System.out.println ("Error opening the file "+locationFileName);
	        System.exit (0);
	    }
		outputToFile.println(""+cashInserted);
		outputToFile.println(""+totalChangeInMachine);
		outputToFile.println(""+location);
		for (Dispenser s: dispenser){
			outputToFile.println(""+s.getQuantity());
			outputToFile.println(""+s.getFood().getName());
			outputToFile.println(""+s.getFood().getCal());
			outputToFile.println(""+s.getFood().getPrice());
			outputToFile.println(""+s.getFood().getSugar());
			outputToFile.println(""+s.getFood().getType());
			outputToFile.println(""+s.getFood().getFat());
			outputToFile.println(""+s.getFood().getImgUrl());
		}
		outputToFile.close();
		//Append to RunFile
		boolean found = false;
		File runFile = new File("runFile.txt");
		if (runFile.exists()){
		try{
			inputStream = new Scanner(runFile);//open Scanner to runFile to read	  
		}
		catch (FileNotFoundException e){
	    	System.out.println("Error opening runfile");
	        System.exit(0); 
		}
		while (inputStream.hasNextLine()) {
		    String line = inputStream.nextLine();
		    if (line.equals("VendingMachine_"+location+".txt")) {
		        found = true;
		        break;
		    }
		}
		inputStream.close();
		}
		if (!found) {
		    try {
		    	outputToFile = new PrintWriter(new FileWriter(runFile, true));//
		    }
		    catch (Exception e) {
		    	System.out.println("Error writing to runfile");
		        System.exit(0); 			    	
		    }
	    	outputToFile.println("VendingMachine_"+location+".txt");
	    	outputToFile.close();
		}
	}
	
	private void printDaysBusiness(){
		//print daysSales, totalChangeInMachine, and each dispensers daysSales to file
		try {
			outputToFile = new PrintWriter (dateFileName+"_"+location+".txt");
	    }
	    catch (FileNotFoundException e) {
	        System.out.println ("Error opening the file "+dateFileName);
	        System.exit (0);
	    }
		outputToFile.println("Vending Machine Location: "+location);
		outputToFile.println("Total Days Sales = $"+daysSales);
		outputToFile.println("Total Money in Machine $: "+totalChangeInMachine);
		int i=1;
		for (Dispenser s: dispenser){
			outputToFile.println("Dispenser "+i+": "+s.getFood().getName()+"\n"+
			"Quantity Sold: "+s.getDaysSales()+"\n"+
			"Quantity Left: "+s.getQuantity()+"\n"+
			"Amount Sold: $"+(s.getFood().getPrice()*s.getDaysSales())+"\n");
			i++;
		}
		outputToFile.close();
	}	
	public void recordSale(Dispenser itemChosen){
		itemChosen.decQuantity();
		daysSales+=itemChosen.getFood().getPrice();
		totalChangeInMachine+=itemChosen.getFood().getPrice();
		cashInserted=(double)(Math.round((cashInserted-itemChosen.getFood().getPrice())*100))/100;
	}
	private void printReceipt(Dispenser itemChosen){
		System.out.println("RECEIPT FOR: "+itemChosen.getFood());
		System.out.println("COST: $"+itemChosen.getFood().getPrice());
		System.out.println("Money entered: $"+((double)(Math.round((cashInserted+itemChosen.getFood().getPrice())*100))/100));
		vendChange();
	}
	public void vendChange(){
		System.out.println("Your change is: $"+cashInserted);
		System.out.println("Thank You!\n//");
		cashInserted=0;
	}
	private Dispenser[] createRandomDispensers(int numOfDispensers, int stockInEach){
		Dispenser[] d = new Dispenser[numOfDispensers];
		for (int i=0;i<numOfDispensers;i++){ //loop to add numOfDispensers unique food item dispensers to this vending machine
			d[i] = new Dispenser();
			boolean alreadyInArray;
			do {
				int randNum = (int)(Math.round((Math.random()*20)));
				Dispenser randomDispenser = new Dispenser(randNum, stockInEach);//create a dispenser with a random food selection
				alreadyInArray=false;
				for (int j=0;j<=i;j++){ 
					if (d[j].getFood().equals(randomDispenser.getFood())){
						alreadyInArray=true;
					}
				}
				if (!alreadyInArray){
					d[i]=randomDispenser;
				}
			} while (alreadyInArray);//end 1-10 assignment loop	
		}
		return d;
	}
	
	
}
