grammar SmallC;

options {
  language = Java;
}

@header {
	package ua.compilers.Grammar;
	import java.util.HashMap;
	import ua.compilers.Tree.*;
}

@lexer::header {
	package ua.compilers.Grammar;
	import ua.compilers.Tree.*;
}

@members {
  HashMap memory = new HashMap();
  double temp = 0.0;	// Temporary variable for calculations
  Tree rpnTree = new Tree();	// Our AST Tree
}

prog: stdio? preDeclarations main postDeclarations;

stdio
  : '#include ' '<stdio.h>';

preDeclarations
	: (functionDeclaration | variableDeclaration)*;

main
  : typeSpecifier 'main' parameters '{' statement+ '}'{
System.out.println(rpnTree.walk());	// Walk the Tree - Print the output
};

postDeclarations
	: functionDeclaration*;

variableDeclaration
	: declaration SEMICOLON;

functionDeclaration
	: variableDeclaration parameters SEMICOLON;

parameters
	:	'(' 'void'* ')';

statement
  : expression | ifStatement | whileStatement | forStatement | printfStatement
    ;
    
expression: (calculation | declaration | assignment | jumpStatement) SEMICOLON;

printfStatement
	: 'int'? 'printf(' ','? ')' SEMICOLON;

forStatement
	: 'for' '('ifExpression? ';' ifExpression? ';' ifExpression? ')' '{' statement* '}'
	;

whileStatement
	: 'while' '(' ifExpression ')' '{' statement* '}'
	;

ifStatement
	: 'if' '(' ifExpression ')' '{' statement* '}'
		( 'else' '{' statement* '}' )?
	;

ifExpression
	: assignment | IDENTIFIER  | calculation | equalityCheck
	;

functionExpression
  : assignment (',' assignment)*
  ;

equalityCheck
	: (IDENTIFIER | numbers ) equalitySign (IDENTIFIER | numbers)
	;

equalitySign
	: '==' | '>' | '<' | '>=' | '<=' | '&&' | '||'
	;

assignment:
  IDENTIFIER EQ calculation;

declaration:
  typeSpecifier IDENTIFIER EQ numbers | 'typedef' typeSpecifier IDENTIFIER | typeSpecifier IDENTIFIER | 'const' typeSpecifier IDENTIFIER EQ numbers;
    
calculation
	: IDENTIFIER '++' | IDENTIFIER '--' | (numbers | calculationSymbols)+
	;

numbers : atom+;

atom
  : number
{
rpnTree.addOperation(new Atom(Double.parseDouble($number.text))); // Store the new number to the Tree
}
  ;

calculationSymbols : symbol+ (COMMENT | LINE_COMMENT)?;

symbol
:
PLUS
{
rpnTree.addOperation(new Addition()); // Create new Addition object and store it the ArrayList<Operation>
}
|
MINUS
{
rpnTree.addOperation(new Subtraction()); // Create new Subtraction object and store it the ArrayList<Operation>
}
|
DIV
{
rpnTree.addOperation(new Division()); // Create new Division object and store it the ArrayList<Operation>
}
|
POWER
{
rpnTree.addOperation(new Exponentiation()); // Create new Exponentiatino object and store it the ArrayList<Operation>
}
|
STAR
{
rpnTree.addOperation(new Multiplication()); // Create new Multiplication object and store it the ArrayList<Operation>
}
|
MOD
{
rpnTree.addOperation(new Modulo()); // Create new Modulo object and store it the ArrayList<Operation>
}
;
    
number
    : (INT | DOUBLE) // Accept integer and double type numbers
    ;

// else,
//if, while, typedef
//for

    
jumpStatement
  : 'continue'
  | 'break'
  | 'return'
  | 'return' (functionExpression | number)
  ;
    
// Tokens
typeSpecifier
  : 'void'
  | 'char'
  | 'int'
  | 'float'
  ;

COMMENT
    :   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;
    
IDENTIFIER
  : LETTER (LETTER|'0'..'9')*
  ;
  
fragment
LETTER
  : 'A'..'Z'
  | 'a'..'z'
  | '_'
  ;

EQ: '=';
SEMICOLON: ';';    
STAR : '*';
DIV : '/';
MINUS : '-';
POWER : '^';
PLUS : '+';
MOD : '%';
INT : '0'..'9'+;
DOUBLE : ('0'..'9')+'.' ('0'..'9')+; // In case of double, accept at least one digit before the '.' symbol and one after
//NEWLINE : '\r'?'\n';
WS : (' '|'\t'|'\n'|'\r') {$channel = HIDDEN;};