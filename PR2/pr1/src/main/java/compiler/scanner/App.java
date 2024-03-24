package compiler.scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.CMinusParser;
import compiler.parser.Parser;

import compiler.scanner.Token.TokenType;
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
    public static void main(String[] args) throws FileNotFoundException {
        // Go up one directory level to find the test file
        String WorkingDir = System.getProperty("user.dir") + "\\src\\main\\java\\compiler\\scanner\\test\\";
        System.out.println(WorkingDir);
        // Ask user for whitch test file to use
        System.out.println("Enter the name of the file you would like to test DIGIT INPUT:");
        
        java.util.Scanner input = new java.util.Scanner(System.in);
        String filenumber = input.nextLine();
        input.close();
        String fileNamePath = WorkingDir +  "test" + filenumber + ".c";
        
        // Initialize the scanner
        Scanner scanner = new CminusScanner2(fileNamePath);
        try {
            FileWriter myWriter = new FileWriter(WorkingDir + "output.txt");

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
                    //write to file
                    myWriter.write(line + "\n");
                }
            }
            myWriter.close();
            
            System.out.println("Successfully wrote to the file. ONTO PARSE1");
            Parser parseVar = new CMinusParser(WorkingDir + "output.txt");
            parseVar.parse();
            
            
            System.out.println(parseVar.getClass());

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
