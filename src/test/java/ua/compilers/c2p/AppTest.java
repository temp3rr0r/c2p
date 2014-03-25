package ua.compilers.c2p;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import ua.compilers.FileHandler.FileHandler;
import ua.compilers.Grammar.SmallCLexer;
import ua.compilers.Grammar.SmallCParser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }
    
	public void test() throws IOException, RecognitionException {
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
	
	public void test2() throws IOException, RecognitionException {
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
				"if (k)\n" +
				"{\n" +
				"continue;\n" +
				"}\n" +
				"continue;\n" +
				"}\n" +
				"while(l){\n" +
				"3 5 *;\n" +
				"}\n" +
				"}\n";

			InputStream is = new ByteArrayInputStream( testFile.getBytes("UTF-8") );

			ANTLRInputStream input = new ANTLRInputStream(is);
			SmallCLexer lexer = new SmallCLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			
			SmallCParser parser = new SmallCParser(tokens);		
			parser.prog();
			System.out.println(testFile);
	}

	public void test3() throws IOException, RecognitionException {
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
				"if (k)\n" +
				"{\n" +
				"continue;\n" +
				"}\n" +
				"continue;\n" +
				"}\n" +
				"while(l){\n" +
				"3 5 *;\n" +
				"}\n" +
				"}\n";

			InputStream is = new ByteArrayInputStream( testFile.getBytes("UTF-8") );

			ANTLRInputStream input = new ANTLRInputStream(is);
			SmallCLexer lexer = new SmallCLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			
			SmallCParser parser = new SmallCParser(tokens);		
			parser.prog();
			System.out.println(testFile);
	}
	
	public void test4() throws IOException, RecognitionException {
		String testFile = "int main(void)\n"
		+ "{\n"		
		+  "// Needs parser to see fault\n"
        + "if (1 == 1)\n"
		+ "}\n";

		InputStream is = new ByteArrayInputStream( testFile.getBytes("UTF-8") );

		ANTLRInputStream input = new ANTLRInputStream(is);
		SmallCLexer lexer = new SmallCLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		System.out.println(testFile);
		SmallCParser parser = new SmallCParser(tokens);		
		parser.prog();
	}

	public void test5() throws IOException, RecognitionException {
		String testFile = "int main(void)\n"
		+ "{\n"		
		+ "int i;\n"
		+ "for(i = 1; i < 5)\n"
		+ "{\n"
		+ "}\n"
		+ "}\n";

		InputStream is = new ByteArrayInputStream( testFile.getBytes("UTF-8") );

		ANTLRInputStream input = new ANTLRInputStream(is);
		SmallCLexer lexer = new SmallCLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		System.out.println(testFile);
		SmallCParser parser = new SmallCParser(tokens);		
		parser.prog();
	}
	
	public void testBad2() throws IOException, RecognitionException {
		String filePath = "src\\test\\java\\inputCFiles\\bad2.c";
		
		String testFile = FileHandler.readFile(filePath);
		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(filePath),"UTF-8");
		SmallCLexer lexer = new SmallCLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		System.out.println(testFile);
		SmallCParser parser = new SmallCParser(tokens);		
		parser.prog();
	}
	
	public void testBad1() throws IOException, RecognitionException {
		String filePath = "src\\test\\java\\inputCFiles\\bad1.c";
		
		String testFile = FileHandler.readFile(filePath);
		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(filePath),"UTF-8");
		SmallCLexer lexer = new SmallCLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		System.out.println(testFile);
		SmallCParser parser = new SmallCParser(tokens);		
		parser.prog();
	}
	
	public void testBad3() throws IOException, RecognitionException {
		String filePath = "src\\test\\java\\inputCFiles\\bad3.c";
		
		String testFile = FileHandler.readFile(filePath);
		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(filePath),"UTF-8");
		SmallCLexer lexer = new SmallCLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		System.out.println(testFile);
		SmallCParser parser = new SmallCParser(tokens);		
		parser.prog();
	}
	
	public void testGood() throws IOException, RecognitionException {
		String filePath = "src\\test\\java\\inputCFiles\\good.c";
		
		String testFile = FileHandler.readFile(filePath);
		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(filePath),"UTF-8");
		SmallCLexer lexer = new SmallCLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		System.out.println(testFile);
		SmallCParser parser = new SmallCParser(tokens);		
		parser.prog();
	}

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
