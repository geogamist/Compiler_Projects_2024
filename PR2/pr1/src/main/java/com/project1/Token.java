package com.project1;


public class Token {
    public enum TokenType {
    	ENDFILE, ERROR,
    	IF, THEN, ELSE, END, REPEAT, UNTIL, READ, WRITE,
    	INT, RETURN, VOID, WHILE,
    	ID, NUM,
    	ASSIGN, EQ, LT, PLUS, MINUS, TIMES, OVER, LPAREN,
    	RPAREN, LBRACKET, RBRACKET, SEMI
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
