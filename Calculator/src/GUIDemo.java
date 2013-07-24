
import infix.InFixEvaluator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import postfix.*;
import prefix.PreFixEvaluator;

public class GUIDemo {
	// text field
	private static JTextField expressionText; // text field for expression
	private static double result = 0;
	
	// Define Action Listeners
	private static class ActionButton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// listener for "=" button
			if ((event.getActionCommand().equals("="))||(event.getActionCommand().equals(expressionText.getText()))) {
				String errMessage = null;

				try {
					// Precondition, must be in either postfix, prefix, or infix
					int indexSpace = expressionText.getText().indexOf(' ') + 1;
					if ((expressionText.getText().contains(")") || ((expressionText.getText().substring(0, 1)
							.matches("[0-9]")) && (!(expressionText.getText().substring(indexSpace,
							indexSpace + 1).matches("[0-9]")))))) {
System.out.println("InFix");
						result = Double.parseDouble(InFixEvaluator.evaluate(expressionText.getText()));
					} else if (expressionText.getText().substring(0, 1).matches("[0-9]")) {
System.out.println("PostFix");
						result = PostFixEvaluator.evaluate(expressionText.getText());
					} else
						result = PreFixEvaluator.evaluate(expressionText.getText());

					// Output result.
					System.out.println();
					System.out.println("Result = " + result);
					expressionText.setText(""+result);

				} catch (Exception error) {
					// Output error message.
					errMessage = error.getMessage();
					expressionText.setText("Error: " + errMessage);
				}
			} else if (event.getActionCommand().equals("C")) {
				expressionText.setText("");
			} else {
				if ((expressionText.getText().equals(""+result))||(expressionText.getText().equals("Enter an expression"))){
					expressionText.setText(""+event.getActionCommand()+" ");					
				} else {
						expressionText.setText(expressionText.getText()+event.getActionCommand()+" ");
				}			
			}
		}
	}
//	0123456789.+-*/()=C


	public static void main(String args[]) throws IOException {
		// Declare/instantiate/initialize display frame.
		JFrame displayFrame = new JFrame();
		displayFrame.setTitle("Calculator Pre, Post, or In");
		displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		displayFrame.setResizable(false);
		displayFrame.setLocation(530, 220);

		// text box for expression
		expressionText = new JTextField("Enter an expression", 15);
		expressionText.setHorizontalAlignment(JTextField.RIGHT);
		expressionText.setFont(expressionText.getFont().deriveFont(20f));

		// Buttons
		JButton plusBtn = new JButton("+");
		JButton minusBtn = new JButton("-");
		JButton multiplyBtn = new JButton("*");
		JButton divideBtn = new JButton("/");
		JButton sevenBtn = new JButton("7");
		JButton eightBtn = new JButton("8");
		JButton nineBtn = new JButton("9");
		JButton openBtn = new JButton("(");
		JButton fourBtn = new JButton("4");
		JButton fiveBtn = new JButton("5");
		JButton sixBtn = new JButton("6");
		JButton closeBtn = new JButton(")");
		JButton oneBtn = new JButton("1");
		JButton twoBtn = new JButton("2");
		JButton threeBtn = new JButton("3");
		JButton zeroBtn = new JButton("0");
		JButton clearBtn = new JButton("C");
		JButton dotBtn = new JButton(".");
		JButton equalsBtn = new JButton("=");

		//Change Button Dimensions
		plusBtn.setPreferredSize(new Dimension (40,40));
		
		// Button event listener
		ActionButton btnListener = new ActionButton();

		// Register button listeners
		plusBtn.addActionListener(btnListener);
		minusBtn.addActionListener(btnListener);
		multiplyBtn.addActionListener(btnListener);
		divideBtn.addActionListener(btnListener);
		sevenBtn.addActionListener(btnListener);
		eightBtn.addActionListener(btnListener);
		nineBtn.addActionListener(btnListener);
		openBtn.addActionListener(btnListener);
		fourBtn.addActionListener(btnListener);
		fiveBtn.addActionListener(btnListener);
		sixBtn.addActionListener(btnListener);
		closeBtn.addActionListener(btnListener);
		oneBtn.addActionListener(btnListener);
		twoBtn.addActionListener(btnListener);
		threeBtn.addActionListener(btnListener);
		zeroBtn.addActionListener(btnListener);
		clearBtn.addActionListener(btnListener);
		dotBtn.addActionListener(btnListener);
		equalsBtn.addActionListener(btnListener);
		expressionText.addActionListener(btnListener);


		// Instantiate content pane and information panels.
		Container contentPane = displayFrame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		JPanel expressionPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();

		// Initialize expression panel.
		expressionPanel.setLayout(new GridLayout(1, 1));
		expressionPanel.add(expressionText);
		expressionPanel.setPreferredSize(new Dimension(200,50));

		// Initialize button panel.
		buttonsPanel.setLayout(new GridLayout(5, 4));
		buttonsPanel.add(plusBtn);
		buttonsPanel.add(minusBtn);
		buttonsPanel.add(multiplyBtn);
		buttonsPanel.add(divideBtn);
		buttonsPanel.add(sevenBtn);
		buttonsPanel.add(eightBtn);
		buttonsPanel.add(nineBtn);
		buttonsPanel.add(openBtn);
		buttonsPanel.add(fourBtn);
		buttonsPanel.add(fiveBtn);
		buttonsPanel.add(sixBtn);
		buttonsPanel.add(closeBtn);
		buttonsPanel.add(oneBtn);
		buttonsPanel.add(twoBtn);
		buttonsPanel.add(threeBtn);
		buttonsPanel.add(equalsBtn);
		buttonsPanel.add(zeroBtn);
		buttonsPanel.add(clearBtn);
		buttonsPanel.add(dotBtn);

		// Set up and show the frame.
		contentPane.add(expressionPanel, "North");
		contentPane.add(buttonsPanel, "Center");

		displayFrame.pack();
		displayFrame.setVisible(true);
	}
}
