import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class mainGUI {	
	
	public static void main(String[] args) {
		Scanner inputStream = null;
		ArrayList<VendingMachineGUI> vMGUIList = new ArrayList<VendingMachineGUI>();
		VendingMachineGUI vMachineGUI;
		
	/**Check runFile to see if there is data saved from previous day of business
	   If not, then create 2 new machines */
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
				VendingMachine v1 = VendingMachine.restore(inputStream.nextLine());
				vMachineGUI = new VendingMachineGUI(v1);
				vMGUIList.add(vMachineGUI);
			}
			inputStream.close();
		}
		else {
				vMachineGUI = new VendingMachineGUI("First Floor");
				vMGUIList.add(vMachineGUI);
				vMachineGUI = new VendingMachineGUI("Third Floor");
				vMGUIList.add(vMachineGUI);
		}//Machines are built!
		
		
//Display the Vending Machine GUIs 		
		for (int i=0;i<vMGUIList.size();i++){
			vMGUIList.get(i).display();
		}
	}

}//Class