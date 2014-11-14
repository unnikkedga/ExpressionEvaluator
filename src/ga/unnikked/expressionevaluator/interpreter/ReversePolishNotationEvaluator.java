package ga.unnikked.expressionevaluator.interpreter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import static ga.unnikked.expressionevaluator.utils.Util.*;

public class ReversePolishNotationEvaluator {
    private LinkedList<String> stack = new LinkedList<String>();
    private String bytecode;

    public ReversePolishNotationEvaluator(String expression) {
        this.bytecode = expression;
    }
	public HashMap<String, Double> context = new HashMap<String, Double>();
	
    public Double evaluate() {
	    System.out.println(bytecode);
	    String code[] = bytecode.split(" ");
        String instruction;
	    double firstOperand, secondOperand;
	    int instructionPointer = 0; //instruction pointer
	    //System.out.println("IP,\tCode[IP],\tSTACK");
	    while (instructionPointer < code.length && instructionPointer > -1) {
		    //System.out.printf("%d,\t%s,\t\t%s\n", instructionPointer, code[instructionPointer], stack);
            instruction = code[instructionPointer++];
		    if(isNumber(instruction)) {
                stack.push(instruction);
            } else {
			    if (instruction.equals("^")) {
				    secondOperand = cast(stack.pop());
				    firstOperand = cast(stack.pop());
				    stack.push("" + Math.pow(firstOperand, secondOperand));
			    } else if (instruction.equals("/")) {
				    secondOperand = cast(stack.pop());
				    firstOperand = cast(stack.pop());
				    stack.push("" + (firstOperand / secondOperand));
			    } else if (instruction.equals("*")) {
				    secondOperand = cast(stack.pop());
				    firstOperand = cast(stack.pop());
				    stack.push("" + (firstOperand * secondOperand));
			    } else if (instruction.equals("+")) {
				    secondOperand = cast(stack.pop());
				    firstOperand = cast(stack.pop());
				    stack.push("" + (firstOperand + secondOperand));
			    } else if (instruction.equals("-")) {
				    secondOperand = cast(stack.pop());
				    if(stack.peek() != null && isNumber(stack.peek())) { // unary minus
				    	firstOperand = cast(stack.pop());
				    	stack.push("" + (firstOperand - secondOperand));
				    } else {
				    	stack.push("" + (-secondOperand));
				    }
			    } else if (instruction.equals("sin")) {
		            firstOperand = cast(stack.pop());
		            stack.push("" + Math.sin(firstOperand));
	            } else if (instruction.equals("cos")) {
		            firstOperand = cast(stack.pop());
		            stack.push("" + Math.cos(firstOperand));
	            } else if (instruction.equals("atan")) {
		            firstOperand = cast(stack.pop());
		            stack.push("" + Math.atan(firstOperand));
	            } else if (instruction.equals("ln")) {
		            firstOperand = cast(stack.pop());
		            stack.push("" + Math.log(firstOperand));
	            } else if (instruction.equals("exp")) {
		            firstOperand = cast(stack.pop());
		            stack.push("" + Math.exp(firstOperand));
	            } else if (instruction.equals("ack")) {
	            	secondOperand = cast(stack.pop());
		            firstOperand = cast(stack.pop());
		            stack.push("" + ackermann(firstOperand, secondOperand));
	            }else {
		            throw new RuntimeException("Unknown instruction: " + instruction);
	            }
            }
        }
        return cast(stack.pop());
    }

	private Double cast(String token) {
		return Double.parseDouble(token);
	}

	public double ackermann(double m, double n) {
		if (m < 0 || n < 0) {
			throw new IllegalArgumentException("Non-negative args only!");
		}

		if (m == 0) {
			return n + 1;
		} else if (n == 0) {
			return ackermann(m-1, 1); // Corrected!
		} else {
			// perforce (m > 0) && (n > 0)
			return ackermann(m-1, ackermann(m,n-1));
		}
	}
}
