package com.project1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

}
public class Token {
    private TokenType tokenType;
    private Object tokenData;
    public Token (type) {
    this (type, null);
    }
    public Token (TokenType type, Object data) {
    tokenType = type;
    tokenData = data;
    }
    // some access methods
    }