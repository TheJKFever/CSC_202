import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**PLEASE READ
*This is a driver program to simulate random Vending Machine transactions
*If there is a restore file than machines are restored from the day before. 
*if not, 2 new Machines are created
*customers come rapidly one after another and 
*randomly choose which machine to go to (1/3 chance of not going to any machine)
*randomly choose how much to put into the machine (below $3 incredments of 25 cents)
*randomly choose what to purchase (1 of 10 options)
*if they're purchase is successful, they take their change (if any) and a new customer comes.
*if there are no items left or they didn't put in enough money
*they have the option to make another choice, put in more money, or get their change and walk away
*there is a 1 in 100 chance that the machines will turn off. 
*Immediately before they turn off, they save the days sales to seperate files
*they also save a restore file for use the next day.
**/

public class mainConsole {
	
	public static void main(String[] args) {
		
		Scanner inputStream = null;		
		VendingMachine tempVM = new VendingMachine(0);
		ArrayList<VendingMachine> vMList = new ArrayList<VendingMachine>();
		Random r = new Random();
		int rndMachine, rndmChoice;
		double rndmChange;
		boolean walkAway;
		VendingMachine vMachine;
		
		/**Check runFile to see if there is data saved from previous day of business
		 * If not, then create 2 new machines 
		*/
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
				vMachine = VendingMachine.restore(inputStream.nextLine());
				vMList.add(vMachine);
			}
			inputStream.close();
		}
		else {
				vMachine = new VendingMachine("First Floor");
				vMList.add(vMachine);
				vMachine = new VendingMachine("Third Floor");
				vMList.add(vMachine);				
		}//Machines are built! 
		
		//Bring on the customers
		for (int i=1;i>0;i++){
			rndMachine = (r.nextInt(3)+1);
			if(rndMachine!=3){ // 2/3 chance of having a customer 
				//Pick which Machine to go to
				//Make sure that machine has stock to purchase
				if (rndMachine==1) {
					if (vMList.get(0).outOfStock()){
						vMList.set(0, vMList.get(0).reStock());
					}					
					tempVM = vMList.get(0);
				}
				else {
					if (vMList.get(1).outOfStock()){
						vMList.set(1, vMList.get(1).reStock());
					}					
					tempVM = vMList.get(1);
				}
				//Machine has stock :) Begin your purchase
				rndmChange = ((double)r.nextInt(12)*25)/100; //gets a dollar amount under $3 in 25cent increments
				System.out.println("Customer #"+i+" decides to go to Machine #"+rndMachine+" and adds $"+rndmChange);
				tempVM.addChange(rndmChange);
				rndmChoice = r.nextInt(10); //picks random vending machine dispenser
				System.out.println("Customer #"+i+" decides to buy "+tempVM.getDispenser()[rndmChoice].getFood().getName());
				walkAway=false;
				while (tempVM.getDispenser()[rndmChoice].getQuantity()==0){ //OUT OF STOCK, choose again or walk away
					tempVM.makeSale(tempVM.getDispenser()[rndmChoice]); //Makes the sales
					System.out.println("WOOPS! That option is out of stock!");
					walkAway=(r.nextInt(2)==1);//50/50 chance of walking away
					if (walkAway){break;}
					else {	
						rndmChoice = r.nextInt(10); //picks random vending machine dispenser
						System.out.println("Customer #"+i+" decides to choose "+tempVM.getDispenser()[rndmChoice].getFood().getName()+" instead");
						System.out.println("Let's try again");
					}
				}
				while (tempVM.getCashInserted()<tempVM.getDispenser()[rndmChoice].getFood().getPrice()){//NOT ENOUGH $, add or walk away
					tempVM.makeSale(tempVM.getDispenser()[rndmChoice]); //Makes the sales
					System.out.println("WOOPS! Customer #"+i+" did not put in enough money to purchase "+tempVM.getDispenser()[rndmChoice].getFood().getName());
					walkAway=(r.nextInt(2)==1);//50/50 chance of walking away
					if (walkAway){break;}
					else {	
						rndmChange = ((double)r.nextInt(12)*25)/100; //gets a dollar amount under $3 in 25cent increments
						System.out.println("Customer #"+i+" decides to adds $"+rndmChange+" more");
						tempVM.addChange(rndmChange);
						System.out.println("Let's try again");
					}
				}
				if (walkAway){
					System.out.println("Customer #"+i+" decides to get his change back and walk away.");
					tempVM.vendChange();	
				}
				else{ //have stock, and have enough money, make sale
					tempVM.makeSale(tempVM.getDispenser()[rndmChoice]); 
				}
				if(r.nextInt(100)==5){ //System exit one out of a 200 chances.
					vMList.get(0).closeMachine();
					vMList.get(1).closeMachine();
					System.exit(0);
				}
			}//BODY
			else {
				System.out.println("No customer...");
			}//else for no customer
		}//for loop
	}//End main
}//End class
