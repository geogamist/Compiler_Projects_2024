package com.project1;


public class Token {
    public enum TokenType {
    IDENT_TOKEN,
    ASSIGN_TOKEN,
    IF_TOKEN, 
    EOF_TOKEN,
    // rest of tokens ....
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
