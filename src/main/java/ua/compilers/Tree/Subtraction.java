package ua.compilers.Tree;

// Class that describes the subtraction operation 

public class Subtraction extends Operation {
	
	public Subtraction() {
        Double temp = Tree.numberStack.pop();	// Pop the last 2 elements, subtract them and push the result
        Tree.numberStack.push(Tree.numberStack.pop() - temp);
	}
	
	public String getCommand() {	// Generated Pmachine code
		return "sub r\n";
	}
}
