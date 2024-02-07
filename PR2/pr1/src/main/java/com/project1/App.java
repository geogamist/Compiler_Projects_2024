package com.project1;
import com.project1.Token.TokenType;

/**
 * Tests the CminusScanner with a basic addition program
 */
public class App {
    public static void main( String[] args ) {
        String filename = "PR2\\pr1\\src\\main\\java\\test.txt";
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
