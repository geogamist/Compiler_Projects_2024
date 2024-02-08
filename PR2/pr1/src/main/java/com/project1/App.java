package com.project1;
import com.project1.Token.TokenType;

/** 
* This class implements App, providing testing support for the CminusScanner
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: App.java
* Created: Feb 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
* Summary of Modifications:
*
* Description: This class provides a simple testing program for reading an input
* file into the CminusScanner and checking the output.
*/
public class App {
    public static void main( String[] args ) {
        String filename = "PR2\\pr1\\src\\main\\java\\test3.txt";
        Scanner scanner = new CminusScanner(filename);

        // Cycle through the program, collecting and printing all valid tokens
        while (scanner.viewNextToken().getTokenType() != TokenType.ENDFILE) {
            String line = new String();
            Token currToken = scanner.getNextToken();
            TokenType type = currToken.getTokenType();
            line = String.valueOf(type);

            if (type == TokenType.ID || type == TokenType.NUM) {
                line += (" " + String.valueOf(currToken.getTokenData()));
            }
            if (type != TokenType.NULL) {
                System.out.println(line);
            }
        }
    }
}
