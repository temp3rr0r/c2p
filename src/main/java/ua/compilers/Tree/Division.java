package ua.compilers.Tree;


public class Division extends Operation {
	
	public Division() {
        Double temp = Tree.numberStack.pop();
        Tree.numberStack.push(Tree.numberStack.pop() / temp);		
	}
	
	public String getCommand() {	// Generated Pmachine code for the division operation
		return "div r\n";
	}
}
