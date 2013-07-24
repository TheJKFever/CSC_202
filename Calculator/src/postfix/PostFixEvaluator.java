package postfix;

import java.util.Scanner;
import stacks.LinkedStack;
import stacks.UnboundedStackInterface;

public class PostFixEvaluator {
	
	public static double evaluate(String expression) {
	//Precondition - expression is in postfix notation
		UnboundedStackInterface<Double> stack = new LinkedStack<Double>();  
		double operand1, operand2, result=0;
		String operator;
		Scanner tokenizer = new Scanner(expression);
		
		while (tokenizer.hasNext()) {
			if (tokenizer.hasNextDouble()) { //process operand
				stack.push(tokenizer.nextDouble());
			}
			else { //process operator
				operator = tokenizer.next();
	        
	        // Obtain second operand from stack.
		        if (stack.isEmpty()) {
		        	tokenizer.close();
		        	throw new PostFixException("Too many operations - Pop from empty stack");
		        }
		        operand2 = stack.top();
		        stack.pop();
	
	        // Obtain first operand from stack.
		        if (stack.isEmpty()) {
		        	tokenizer.close();
		        	throw new PostFixException("Too many operations - Pop from empty stack");
		        }
		        operand1 = stack.top();
		        stack.pop();
		
	        // Perform operation
		        if (operator.equals("/")) {
		        	if (operand2==0) {
						tokenizer.close();
		        		throw new PostFixException("Illegal operation: divide by zero");
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
		        	throw new PostFixException("Illegal symbol: " + operator);
		        }
		        
	        // Push result of operation onto stack.
		        stack.push(result);
			}
		}

	    // Obtain final result from stack. 
	    if (stack.isEmpty()) {
		    tokenizer.close();
	    	throw new PostFixException("Nothing entered, please enter expression - Pop from empty stack");
	    }
	    result = stack.top();
	    stack.pop();
	
	    // Stack should now be empty.
	    if (!stack.isEmpty()) {
		    tokenizer.close();
	    	throw new PostFixException("Too many operands - Stack not empty");
	    }
	    // Return the final.
	    tokenizer.close();
	    return result;
    }
}