package ua.compilers.Tree;

public class Atom extends Operation
{
	Double number;
	
	public Atom(Double currentNumber) {		// Push the result to the global Tree stack
		this.number = currentNumber;
		Tree.numberStack.push(currentNumber);
	}
	
	public String getCommand() {			// Generated Pmachine code to input a real number
		return "ldc r " + this.number.toString() + "\n";		
	}
}
