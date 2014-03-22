package ua.compilers.Tree;


public class Addition extends Operation {
	
	public Addition() {
        Double temp = Tree.numberStack.pop();// Pop the last 2 elements, add them and push the result
        Tree.numberStack.push(Tree.numberStack.pop() + temp);
	}
	
	public String getCommand() {	// Generated Pmachine code
		return "add r\n";
	}
}
