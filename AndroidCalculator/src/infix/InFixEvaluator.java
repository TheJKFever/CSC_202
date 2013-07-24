package infix;

import java.util.Scanner;
import stacks.LinkedStack;

public class InFixEvaluator {	

	protected static Scanner tokenizer;
	
	public static String evaluate(String expression) {
	//Precondition - expression is in Infix notation
		double operand1 = 0, operand2, result=0;
		String operator;
		
		if (expression.equals("")){
			throw new InFixException("Nothing entered, please enter expression");
		}

		//First evaluate all expressions in Parentheses
		if (expression.contains("(")||expression.contains(")")){
			expression = removeParentheses(expression);
		}

		tokenizer = new Scanner(expression);
		
		//Process multiplication and Division - end with stack
		if ((expression.contains("*"))||(expression.contains("/"))){
			LinkedStack<String> stack = new LinkedStack<String>();
			while(tokenizer.hasNext()){
				if (tokenizer.hasNextDouble()) { //process operand
					stack.push(""+tokenizer.nextDouble());
				} 
				else {
					operator = tokenizer.next();
					if (operator.equals("*")||operator.equals("/")){
						if (!tokenizer.hasNextDouble())
							throw new InFixException("Inproper Syntax, a number or paretheses must follow '*' or '/'");
						operand2 = tokenizer.nextDouble();

						// Obtain first operand from stack.
				        if (stack.isEmpty()) {
				        	tokenizer.close();
				        	throw new InFixException("Inproper Syntax, a number or paretheses must precede '*' or '/' - Pop from empty stack");
				        }
				        operand1 = Double.parseDouble(stack.top());
				        stack.pop();
				        
				        // Perform operation.
				        if (operator.equals("/")) {
				        	if (operand2==0) {
								tokenizer.close();
				        		throw new InFixException("Illegal operation: divide by zero");
							}
				        	result = operand1 / operand2;
				        }
				        else
				        	result = operand1 * operand2;

			        // Push result of operation onto stack.
				        stack.push(""+result);
					} else {
						stack.push(operator); //adds + - and everything else to stack
					}
				}
			}
			//turn stack into String for basic addition and subtraction
			expression = stack.toString();
		}
				
		//Process Addition and Subtraction - end with Double
		tokenizer = new Scanner(expression);
		while(tokenizer.hasNext()){
			if (tokenizer.hasNextDouble()) { //process operand
				operand1 = tokenizer.nextDouble();
			} 
			else {
				operator = tokenizer.next();

				//get operand2 from tokenizer
				if (!tokenizer.hasNextDouble())
					throw new InFixException("Inproper Syntax, a number or paretheses must follow '+' or '-'");
				operand2 = tokenizer.nextDouble();

		        // Perform operation				
				if(operator.equals("+"))
		        	result = operand1 + operand2;
				else if (operator.equals("-"))
		        	result = operand1 - operand2;
				else {
		        	tokenizer.close();
		        	throw new InFixException("Illegal symbol: " + operator);
				}    
				operand1 = result;
			}
			result = operand1;
		}
	    tokenizer.close();
	    return (""+result);
    }
	
	private static String removeParentheses(String expression){
		int open=0;
		for (int i=0;i<expression.length();i++){
			if (expression.charAt(i)=='('){ //set's the opening ( to closest (
				open=i;
			}
			if (expression.charAt(i)==')'){ //Evaluates what's in parentheses and replaces with value
				expression = expression.replace(expression.substring(open, i+1), evaluate(expression.substring(open+1, i)));				
				i=-1;
			}
		}
		return expression;
	}
}