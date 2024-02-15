/** 
* This class implements the Scanner ADT, providing support for the Cminus language.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: CminusScanner.java
* Created: Feb 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
* Summary of Modifications:
*
* Description: This class provides an implementation of a scanner for the Cminus language.
* Users of this class provide an input file for be scanned, and are provided methods to 
* both view and get the next Token.
*/

import java_cup.runtime.*;
import java.util.*;
import java.io.PushbackReader;
import java.io.FileReader;
import compiler.scanner.Token.TokenType;
import java.io.FileNotFoundException;
import java.io.IOException;

%%

%public
%class CminusScanner2
%implements Scanner
%type Token

%unicode

%line
%column

%cup
%cupdebug

%{
    private StringBuilder tokenString = new StringBuilder();
    private PushbackReader inFile;
    private enum StateType {
        START, INEQ, INLT, INGT, INNOT, INDIV, INCOMMENT,
        INCOMMENTEXIT, INNUM, INNUMRROR, INID, DONE
    }

    private Token token(TokenType type) {
        return new Token(type);
    }

    private Token token(TokenType type, Object data) {
        return new Token(type, data);
    }

    public CminusScanner(String filename) {
        try {
            inFile = new PushbackReader(new FileReader(filename));
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Populate the initial token
        try {
            nextToken = scanToken();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Return the nextToken, then get a new token
    * @return Token
    */
    public Token getNextToken () {
        Token returnToken = nextToken;
        if (nextToken.getTokenType() != TokenType.ENDFILE) {
            try {
                nextToken = scanToken();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnToken;
    }

    /**
    * Return the nextToken
    * @return Token
    */
    public Token viewNextToken () {
        return nextToken;
    }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} 

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*


%%

<YYINITIAL> {
    /* keywords */
    "else"                         { return token(TokenType.ELSE); }
    "int"                          { return token(TokenType.INT); }
    "if"                           { return token(TokenType.IF); }
    "return"                       { return token(TokenType.RETURN); }
    "void"                         { return token(TokenType.VOID); }
    "while"                        { return token(TokenType.WHILE); }
    "then"                         { return token(TokenType.THEN); }
    "end"                          { return token(TokenType.END); }
    "repeat"                       { return token(TokenType.REPEAT); }
    "until"                        { return token(TokenType.UNTIL); }
    "read"                         { return token(TokenType.READ); }
    "write"                        { return token(TokenType.WRITE); }

    /* separators */
    "("                            { return token(TokenType.LPAREN); }
    ")"                            { return token(TokenType.RPAREN); }
    "{"                            { return token(TokenType.LBRACE); }
    "}"                            { return token(TokenType.RBRACE); }
    "["                            { return token(TokenType.LBRACKET); }
    "]"                            { return token(TokenType.RBRACKET); }
    ";"                            { return token(TokenType.SEMI); }
    ","                            { return token(TokenType.COMMA); }

    /* operators */
    "="                            { return token(TokenType.ASSIGN); }
    ">"                            { return token(TokenType.GT); }
    "<"                            { return token(TokenType.LT); }
    "=="                           { return token(TokenType.EQ); }
    "<="                           { return token(TokenType.LE); }
    ">="                           { return token(TokenType.GE); }
    "!="                           { return token(TokenType.NE); }
    "+"                            { return token(TokenType.PLUS); }
    "-"                            { return token(TokenType.MINUS); }
    "*"                            { return token(TokenType.TIMES); }
    "/"                            { return token(TokenType.OVER); }

    /* numeric literals */
    {DecIntegerLiteral}            { return token(TokenType.NUM, Integer.valueOf(yytext())); }

    /* comments */
    {Comment}                      { /* ignore */ }

    /* whitespace */
    {WhiteSpace}                   { /* ignore */ }

    /* identifiers */
    {Identifier}                   { return token(TokenType.ID, yytext()); }
}

<<EOF>>                          { return token(TokenType.ENDFILE); }