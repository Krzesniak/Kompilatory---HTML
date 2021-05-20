grammar Grm;

//Start
prog: components element EOF                                               #Program;

components : COMPONENTS '{' component_definition* '}';
component_definition : ID ('(' id_args ')')? '{' htmlable* '}';

component : ID LeftParen expr_args RightParen ;

repeat : REPEAT LeftParen expr RightParen '{' htmlable* '}';

each : EACH LeftBracket expr_args RightBracket AS ID '{' htmlable* '}';

switche: SWITCH LeftParen expr RightParen '{' switch_line* else_line'}';
switch_line: expr Arrow htmlable;
else_line: ELSE Arrow htmlable;

element
    : ID args '{' code_block? htmlable* '}'                                 #HTML_element
    | expr                                                                  #HTML_inner;

//evaluates to string or integer
expr
    : expr Plus expr                                                        #Add
    | expr Minus expr                                                       #Subtract
    | expr Star expr                                                        #Multiply
    | expr Mod expr                                                         #Modulo
    | expr Less expr                                                        #Less
    | expr Greater expr                                                     #Greater
    | expr Equal expr                                                       #Equal
    | VAR_ID                                                                #Var
    | NUMBER                                                                #Number
    | STRING                                                                #String
    | '[' (expr ',')* expr ']'                                              #Liste
    | expr '[' expr ']'                                                     #ListElement
    | 'STRING(' expr ')'                                                    #FunString;

//evaluates to html
htmlable
    : repeat
    | each
    | switche
    | element
    | component;

expr_args : ( | (expr Comma)* expr);
id_args : ( | (ID Comma)* ID);
args: ('(' (ID '=' expr ','? WS*)* ')')?;

assignment : VAR_ID '=' expr;
code_block : '<' assignment+ '>';

/*
 * Lexer Rules
 */

fragment Letter: [a-zA-Z];
fragment Char: [a-zA-Z0-9_];
fragment Digit:   [0-9];

REPEAT : 'repeat';
EACH : 'each';
AS : 'as';
SWITCH : 'switch';
ELSE : 'else';


COMPONENTS : 'components';
MACROS : 'macros';

LeftBracket         : '[';
RightBracket        : ']';
Comma               : ',';
DoubleQuote         : '"';
LeftParen           : '(';
RightParen          : ')';
Arrow               : '->';

Greater             : '>';
Less                : '<';
Equal               : '==';
Assign              : '=';
Star                : '*';
Mod                 : '%';
Plus                : '+';
Minus               : '-';

ID : Letter Char*;
VAR_ID : '$' Letter Char*;

NUMBER : Minus?[1-9] Digit* | '0';
STRING : DoubleQuote .*? DoubleQuote;

BLOCK_COMMENT :  '/*' .*? '*/' -> skip;
COMMENT : '//' ~[\r\n]* -> skip;
WS : [ \r\t\n]+ -> skip;