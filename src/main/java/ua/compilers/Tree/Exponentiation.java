package ua.compilers.Tree;

public class Exponentiation extends Operation{
	
	// Helpful operation variables
	Double baseNumber;
	int exponent;
	int currentStackSize;
	
	public Exponentiation() {
		this.exponent = (int)(double)Tree.numberStack.pop();
		this.baseNumber = Tree.numberStack.pop();
        Tree.numberStack.push(Math.pow(this.baseNumber, this.exponent));
		this.currentStackSize = Tree.numberStack.size();
	}
	
	public String getCommand() {
		String output = ";power start\n";
		
		switch (this.exponent) {
		case 0:
			output += "; case 0\n";
			output += "sro r " + this.currentStackSize + "\n" + 
					"ldc r 1.0\n" +
					"sro r " + (this.currentStackSize - 1) + "\n";
			break;
		case 1:
			output += "; case 1\n";
			output += "sro r " + this.currentStackSize + "\n";
			break;
		case 2:
			output += "; case 2\n";
			output += "ldo r " + (this.currentStackSize - 1) + "\n" +
					"sro r " + this.currentStackSize + "\n" +
					"mul r\n";
			break;
		default:
			output += "; case default\n";
			output += "ldo r " + (this.currentStackSize - 1) + "\n";
			output += "sro r " + this.currentStackSize + "\n";
			
			for (int i = 0; i <= (this.exponent - 2); i ++) {

				output += "ldo r " + (this.currentStackSize - 1) + "\n";
				output += "mul r\n";
			}
			
			output += "sro r " + (this.currentStackSize - 1) + "\n";
			break;
		}
		
		output += ";power end\n";
				
		return output;
	}
}
