package com.project1;

/** 
* This class implements Tokens, providing support for the scanning.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: Token.java
* Created: Feb 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
* Summary of Modifications:
*
* Description: This class provides an implementation of Tokens. Users are
* provided several methods to set and get the tokens' tokenType and tokenData.
*/
public class Token {
    // Possible token types
    public enum TokenType {
    	/* book-keeping tokens */
    	ENDFILE, NULL, ERROR,
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
    
    // Initializers
    public Token () {
        this (TokenType.NULL, null);
    }
    public Token (TokenType type) {
        this (type, null);
    }
    public Token (TokenType type, Object data) {
	    tokenType = type;
	    tokenData = data;
    }

    // Getters
    public TokenType getTokenType () {
        return tokenType;
    }
    public Object getTokenData () {
        return tokenData;
    }

    // Setters
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
