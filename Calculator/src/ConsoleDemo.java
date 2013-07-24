import java.util.Scanner;
import postfix.*;
import prefix.*;
import infix.*;

public class ConsoleDemo {
	public static void main(String[] args) {

		Scanner conIn = new Scanner(System.in);
	
	    String line = null;          // string to be evaluated
	    String more = null;          // used to stop or continue processing
	    double result=0;             // result of evaluation

	    do { 
	    	// Get the expression to be processed.
	    	System.out.println("Enter a expression in either Postfix, Prefix, or InFix to be evaluated:\n" +
	    			"(Seperate values and operators with spaces)");
	    	line = conIn.nextLine();
	      
			// Obtain and output result of expression evaluation.
			try {
			//Precondition, must be in either postfix, prefix, or infix form
				line = line.trim(); //in case people put spaces before the expression
				int indexSpace = line.indexOf(' ')+1;
				if ((line.contains(")")||
					((line.substring(0,1).matches("[0-9]"))&&
					(!(line.substring(indexSpace,indexSpace+1).matches("[0-9]")))))) {
					result = Double.parseDouble(InFixEvaluator.evaluate(line));
				}
				else if (line.substring(0,1).matches("[0-9]")){
					result = PostFixEvaluator.evaluate(line);
				}
				else
					result = PreFixEvaluator.evaluate(line);
			
				// Output result.
				System.out.println();
				System.out.println("Result = " + result);
			}
			catch (Exception error) {        
				// Output error message.
				System.out.println();
				System.out.println("Error in expression - " + error.getMessage());
			}
	
			// Determine if there is another expression to process.
			System.out.println();
			System.out.print("Evaluate another expression? (Y=Yes): ");
			more = conIn.nextLine();
			System.out.println();
	    }
	    while (more.equalsIgnoreCase("y"));
	
	    conIn.close();
    }
}