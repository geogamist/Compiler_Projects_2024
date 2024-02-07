package com.project1;


public class Token {
    public enum TokenType {
    	/* book-keeping tokens */
    	ENDFILE, ERROR,
    	/* reserved words */
    	IF, THEN, ELSE, END, REPEAT, UNTIL, READ, WRITE,
    	INT, RETURN, VOID, WHILE,
    	/* multicharacter tokens */
    	ID, NUM,
    	/* special symbols */
    	ASSIGN, EQ, LT, GT, LE, GE, NE, PLUS, MINUS, TIMES, OVER, LPAREN,
    	RPAREN, LBRACKET, RBRACKET, LBRACE, RBRACE, SEMI, COMMA
    }
    private TokenType tokenType;
    private Object tokenData;
    // rest of class ....
    public Token (TokenType type) {
        this (type, null);
    }
    
    public Token (TokenType type, Object data) {
	    tokenType = type;
	    tokenData = data;
    }

    // some access methods
    public TokenType getTokenType () {
        return tokenType;
    }
    public Object getTokenData () {
        return tokenData;
    }
    //setters
    public void setTokenType (TokenType type) {
        tokenType = type;
    }
    public void setTokenData (Object data) {
        tokenData = data;
    }
    public void setTokenTypeAndData (TokenType type, Object data) {
        tokenType = type;
        tokenData = data;
    }
}
