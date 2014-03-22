package ua.compilers.Tree;

import java.util.ArrayList;
import java.util.Stack;

// Main Tree walker class, stores every number in an ArrayList in parallel with the Pmachine code generator

public class Tree {
	
	ArrayList<Operation>
	operationsList = new ArrayList<Operation>();	// Linked list that holds "Operation" objects in execution order
	String output= new String();						// The final output string
	public static Stack<Double> numberStack = new Stack<Double>();	// The stack that stores the numeric values

	public void addOperation(Operation newOperation) {	// Public method which pushes a new "Operation" object at
		this.operationsList.add(newOperation);			// the end of the ordered linked list
	}
	
	public void pushStack(Double currentNum) {			// Public method which pushes a new Double number in the global Stack<Double>
		Tree.numberStack.push(currentNum);		
	}
	
	public Double popStack() {							// Pops/gets the last element of the Stack<Double>
		return Tree.numberStack.pop();		
	}
	
	private String addClosure() {						// Finalize the output string
		return "out r\n" + "hlt\n";
	}
	
	public String walk() {
		for(Operation currentOperation : operationsList) {	// Do walk the AST tree and append all the commands in the string "output"
			this.output += currentOperation.getCommand();
		}
		
		this.output += this.addClosure();					// Add the final lines of the Pmachine code
		
		return this.output;		
	}
}
