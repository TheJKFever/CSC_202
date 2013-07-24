import java.lang.Math;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VendingMachineGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean ignoreEvents;	
	private VendingMachine vMachine;
	Image bgImage=null;
	
	//GUI COMPONENTS
	// JPanels
		JPanel displayWindowsP = new JPanel();
		JPanel changeControlsP = new JPanel();
		JPanel dispensersP = new JPanel();
		JPanel offButtonP = new JPanel();
	// JLabels
		BackgroundImagePanel backgroundImageL;
		JLabel mainDisplayL = new JLabel();
		JLabel changeDisplayL = new JLabel();
	// JButtons
		JButton[][] dispensersB = new JButton[5][2];
		JButton vendChangeB = new JButton();
		JButton addChangeB = new JButton();
		JButton offButtonB = new JButton();
	// JTextFields
		JTextField addChangeT = new JTextField(10);
		
	//CONSTRUCTORS
	public VendingMachineGUI() { //Asks for the name of a location in popup
		JFrame popup = new JFrame();
		String location = JOptionPane.showInputDialog(popup,"What is the location of this Vending Machine?",null);
		vMachine = new VendingMachine(location);
		createGUI(vMachine);
		ignoreEvents=false;
	}
	public VendingMachineGUI(VendingMachine vM){ //creates GUI with already preconfigured VMachine, used primarily for restore
		vMachine = vM;
		createGUI(vMachine);
		ignoreEvents=false;
	}
	public VendingMachineGUI(String newLocation){ //Creates a new vMachine with newLocation, and paints it with createGUI
		vMachine = new VendingMachine(newLocation);
		createGUI(vMachine);
		ignoreEvents=false;
	}
	public void createGUI(VendingMachine vMachine) { //Create GUI - called by all constructors	
		// Set panel layouts (LEFT, RIGHT, or Center justified)
		displayWindowsP.setLayout(new FlowLayout(FlowLayout.CENTER));
		displayWindowsP.setOpaque(false);
		changeControlsP.setLayout(new FlowLayout(FlowLayout.CENTER));
		changeControlsP.setOpaque(false);
		dispensersP.setLayout(new GridLayout(5,2,10,10));
		dispensersP.setOpaque(false);
		offButtonP.setLayout(new FlowLayout(FlowLayout.CENTER));
		offButtonP.setOpaque(false);
			try{bgImage = ImageIO.read(new URL("file:/C:/Hard Drive/Programming Projects/CSC 202/VendingMachine/images/Vending Machine.jpg"));}
			catch (Exception e){}
		backgroundImageL = new BackgroundImagePanel(bgImage);
		backgroundImageL.setLayout(new BorderLayout());
	// Set Label fonts //
//		Font bigFont = new Font("Calibri Bold", Font.BOLD, 30);
//		Font midFont = new Font("Calibri Bold", Font.BOLD, 20);
		Font smallFont = new Font("Calibri", Font.PLAIN, 20);
		mainDisplayL.setFont(smallFont);
		changeDisplayL.setFont(smallFont);
		vendChangeB.setFont(smallFont);
		addChangeB.setFont(smallFont);
	// Labels are given string values //
		resetDisplayWindows();
		vendChangeB.setText("Cancel");
		addChangeB.setText("Add Change");
		offButtonB.setText("OFF");
	// Buttons are connected to their Actions
		vendChangeB.addActionListener(new vend());
		addChangeB.addActionListener(new addChange());
		offButtonB.addActionListener(new offAction());
	// Labels, buttons, and textFields are added to their panels //
		displayWindowsP.add(mainDisplayL);
		displayWindowsP.add(changeDisplayL);
		changeControlsP.add(vendChangeB);		
		changeControlsP.add(addChangeB);
		
		int d=0;
		//Set image, add action, and assign button to panel
		for (int j=0;j<5;j++){
			for (int i=0;i<2;i++){
				ImageIcon img = new ImageIcon(vMachine.getDispenser()[d].getFood().getImgUrl());
				dispensersB[j][i] = new JButton();
		        dispensersB[j][i].setIcon(img);
				dispensersB[j][i].addActionListener(new makeSale(d));
				dispensersP.add(dispensersB[j][i]);
				d++;
			}
		}
		offButtonP.add(offButtonB);
		
	// The panels are added in the order that they should appear.
//		add(BorderLayout.CENTER, backgroundImageL);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(displayWindowsP);
		add(changeControlsP);
		add(dispensersP);
		add(offButtonP);
	}

	
	//Display Methods
	/**display() opens up the main display - Call from main*/
	public void display()
	{	JFrame theFrame = new JFrame("Vending Maching: "+vMachine.getLocation());
		theFrame.addWindowListener(new WindowAdapter() { //Turn machine off when click exit
	    @Override
		    public void windowClosing(WindowEvent windowEvent) {
				vMachine.turnOff();
	    	}
		});
//		theFrame.setContentPane(backgroundImageL);
		theFrame.setContentPane(this);
//		theFrame.setPreferredSize(new Dimension(250, 650));
		theFrame.pack();
		theFrame.setVisible(true);
	}
	private void resetDisplayWindows(){
		mainDisplayL.setText("Location: "+vMachine.getLocation());
		changeDisplayL.setText("Current Change: $"+vMachine.getCashInserted());
	}
	
	//Transaction Methods
	public void makeSaleGUI(Dispenser itemChosen){
		try { 
			if(itemChosen.getQuantity()==0){
				throw new Exception ("OUT OF STOCK\n+" +
						"Choose another option;");
			}
			else if (vMachine.getCashInserted()<itemChosen.getFood().getPrice()){
				throw new Exception ("Error: Please enter more money to purchase this item.\n"+
					"You entered: $"+vMachine.getCashInserted()+", this item costs $"+itemChosen.getFood().getPrice()+"\n"+
					"Try Again");
			}
			else {
				vMachine.recordSale(itemChosen);
				printReceiptGUI(itemChosen);
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Warning",2);
		}
	}
	private void printReceiptGUI(Dispenser itemChosen){
	    JFrame receipt = new JFrame("RECEIPT");
	    JTextArea receiptText = new JTextArea(10,40);
	    receiptText.setText("RECEIPT FOR: "+itemChosen.getFood()+"\n"+
	    		"Cost: $"+itemChosen.getFood().getPrice()+"\n"+
	    		"Money entered: $"+((double)(Math.round((vMachine.getCashInserted()+itemChosen.getFood().getPrice())*100))/100)+"\n"+
	    		"Your change is: $"+vMachine.getCashInserted()+"\n"+
	    		"Thank You!");
	    receipt.setSize(280, 200);
	    receipt.setLocation(300,200);
	    receipt.getContentPane().add(BorderLayout.CENTER, receiptText);
	    receipt.addWindowListener(new WindowAdapter() { //Turn machine off when click exit
		    @Override
			    public void windowClosing(WindowEvent windowEvent) {
				resetDisplayWindows();
		    	}
			});
	    receipt.setVisible(true);
		vendChangeGUI();
	}
	public void vendChangeGUI(){
//		mainDisplayL.setText("<html>Your change is: <br/>Thank You!</html>");
		vMachine.setCashInserted(0);
		resetDisplayWindows();
	}

/** Action Listeners for Buttons    */
	//Listen for each dispenser to make a purchase when selected	
	//MakeSale Action
	class makeSale implements ActionListener {
		private int dispenserSelection;		
		public makeSale(int d){
			dispenserSelection = d;
		}
		public void actionPerformed(ActionEvent e) {
			if (!ignoreEvents){
				makeSaleGUI(vMachine.getDispenser()[dispenserSelection]);
			}
			else {}
		}
	}	
	//Vend Action
	class vend implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!ignoreEvents){
				vendChangeGUI();
			}
			else {}
		}		
	}
	//Add Change Action
	class addChange implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!ignoreEvents){
				JFrame popup = new JFrame();
				try {
					double amountToAdd = Double.parseDouble(JOptionPane.showInputDialog(popup,"How much?: ",null));
					if (amountToAdd<0) {
						throw new IllegalArgumentException("Non positive number not allowed");
					} 
					else {
						vMachine.addChange(amountToAdd);
						changeDisplayL.setText("Current Change: $"+vMachine.getCashInserted());
					}
				} catch (NumberFormatException e1) {
					throw new IllegalArgumentException("Invalid entry, please put a dollar amount less than $10 and greater than $0", e1);
				}
			}
			else {}
		}
	}
	//Turn Off Action
	class offAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!vMachine.getOff()){
				ignoreEvents=true;
				vMachine.turnOff();
				mainDisplayL.setText("MACHINE OFF");
				changeDisplayL.setText("");
			}
			else {
				ignoreEvents=false;
				vMachine = VendingMachine.restore("VendingMachine_"+vMachine.getLocation()+".txt");
				resetDisplayWindows();
			}
		}
	}
	
}

@SuppressWarnings("serial")
class BackgroundImagePanel extends JPanel {

   private Image image;

   public BackgroundImagePanel(Image image) {
      this.image = image;
      setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (image != null) {
         g.drawImage(image, 0, 0, null);
      }
   }
}
