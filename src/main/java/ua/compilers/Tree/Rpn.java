package ua.compilers.Tree;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

import ua.compilers.Grammar.SmallCLexer;
import ua.compilers.Grammar.SmallCParser;

public class Rpn {

	public static void main(String[] args) throws Exception{
		
		String testFile = "#include <stdio.h>\n" +
			"int asdf = 3; int asdf = 3;\n" +
			"const char c1 = 30;\n" +
			"//Comments type 1\n" +
			"typedef int tooQuiet;\n" +
			"void main(){\n" +
			"3 5 *;\n" +
			"continue;\n" +
			"/*comments type 2*/\n" +
			"return 1;\n" +
			"if (k)\n" +
			"{\n" +
			"continue;\n" +
			"}\n" +
			"}\n";

		InputStream is = new ByteArrayInputStream( testFile.getBytes("UTF-8") );

		ANTLRInputStream input = new ANTLRInputStream(is);
		SmallCLexer lexer = new SmallCLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		SmallCParser parser = new SmallCParser(tokens);		
		parser.prog();		
		
	}
}
