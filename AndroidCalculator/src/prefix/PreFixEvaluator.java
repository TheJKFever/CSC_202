package prefix;

import java.util.Scanner;
import prefix.PreFixException;
import stacks.LinkedStack;
import stacks.UnboundedStackInterface;

public class PreFixEvaluator {
	
	public static double evaluate(String expression) {
	//Precondition - expression is in prefix notation
		UnboundedStackInterface<String> stack = new LinkedStack<String>();
		double operand1, operand2, result=0.123123123123123123123, doubleCount=0;
		String operator;
		Scanner tokenizer = new Scanner(expression);
		
		while (tokenizer.hasNext()) {
			if (tokenizer.hasNextDouble()) { //process operand
				stack.push(""+tokenizer.nextDouble());
				doubleCount+=1;
				while(doubleCount==2){ //Perform operation
					// Obtain first operand from stack.
					operand2 = Double.parseDouble(stack.top());
		        	stack.pop();

		        	// Obtain second operand from expression.
					operand1 = Double.parseDouble(stack.top());
		        	stack.pop();			        
			        
			        // Obtain operator from stack.
		        	if (stack.isEmpty()) {
						tokenizer.close();
			        	throw new PreFixException("Too many operands - Pop and empty stack");
			        }
	        		operator = stack.top();
			        stack.pop();

			        // Perform operation.
			        if (operator.equals("/")) {
			        	if (operand2==0) {
							tokenizer.close();
			        		throw new PreFixException("Illegal operation: divide by zero");
						}
			        	result = operand1 / operand2;
			        }
			        else if(operator.equals("*"))
			        	result = operand1 * operand2;
			        else if(operator.equals("+"))
			        	result = operand1 + operand2;
			        else if(operator.equals("-"))
			        	result = operand1 - operand2;
			        else {
			        	tokenizer.close();
			        	throw new PreFixException("Illegal symbol: " + operator);
			        }
			        if ((stack.isEmpty())||(!stack.top().substring(0,1).matches("[0-9]"))){
			        	doubleCount=1;
		        	}
			        stack.push(""+result);
				}
			}
			else {
				doubleCount=0;
		        operator = tokenizer.next();
		        stack.push(operator);
			}
		}

	    // Obtain final result from stack. 
	    if (stack.isEmpty()) {
		    tokenizer.close();
	    	throw new PreFixException("Nothing entered, please enter expression");
	    }
	    result = Double.parseDouble(stack.top());
	    stack.pop();	    
	
	    // Stack should now be empty.
	    if (!stack.isEmpty()) {
		    tokenizer.close();
	    	throw new PreFixException("too many operations - expression logic");
	    }
	    // Return the final.
	    tokenizer.close();
	    return result;
    }
}