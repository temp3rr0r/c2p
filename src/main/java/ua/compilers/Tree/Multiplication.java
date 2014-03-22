package ua.compilers.Tree;

public class Multiplication extends Operation {

	public Multiplication() {
        Double temp = Tree.numberStack.pop();// Pop the last 2 elements, divide them and push the result
        Tree.numberStack.push(Tree.numberStack.pop() / temp);		
	}
	
	public String getCommand() {	// Generated Pmachine code
		return "mul r\n";
	}
}
