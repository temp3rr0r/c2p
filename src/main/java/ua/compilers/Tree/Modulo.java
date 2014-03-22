package ua.compilers.Tree;

public class Modulo extends Operation {
	
	// Temporary helpful variables
	int divident;
	int divisor;
	int currentStackSize;
	
	public Modulo() {
        this.divisor = (int)(double)Tree.numberStack.pop();		// Get the divisor from the global stack
        this.divident = (int)(double)Tree.numberStack.pop();	// Get the divident from the global stack
        Tree.numberStack.push((double)(this.divisor == 0 ? 0 :this.divident % this.divisor)); // If the divisor != 0, then perform the modulo operation
        this.currentStackSize = Tree.numberStack.size();       	// Get the current global stack size
	}
	
	public String getCommand() {		
		// Modulo operation: C = A - B*(A / B)		
		String output = "";		
		// If the both A and B are equal or B is zero, set result to 0
		if (this.divisor == this.divident || this.divisor == 0) {
			output = "; modulo = 0\n";
			output += "sro r " + this.currentStackSize + "\n" + 
					"ldc r 0.0\n" +
					"sro r " + (this.currentStackSize - 1) + "\n";
		}
		else {	// In any other case, calculate the modulo operation
			output = "conv r i\n" +							// Convert 2nd real number to integer
			"ldo r " + (this.currentStackSize - 1) + "\n" +	// Load the 1st real number and convert it to integer 
			"conv r i\n" +									
			"sro i " + (this.currentStackSize - 1) + "\n";	// Store it in the first stack position as integer
			
		output += "ldo i " + (this.currentStackSize - 1) + "\n" + 	// Load both divisor and divident again
			"ldo i " + (this.currentStackSize) + "\n";
		
		output += "div i\n" +	// Do the operation  A B A B / * - (from the original A - B*(A / B) in postfix notation) 
			"mul i\n" + 
			"sub i\n";
		output += "conv i r\n";	// Convert result to real
		}
		
		return output;
	}
}
