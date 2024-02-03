package com.project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CminusScanner implements Scanner{

    private BufferedReader inFile;
    private Token nextToken;

    public void CMinusScanner (String filename) throws FileNotFoundException {
        inFile = new BufferedReader(new FileReader(filename));
        nextToken = scanToken();
    }
    public Token getNextToken () {
        Token returnToken = nextToken;
        if (nextToken.getTokenType() != Token.TokenType.EOF_TOKEN)
            nextToken = scanToken();
        return returnToken;
    }
    public Token viewNextToken () {
        return nextToken;
    }
    //scanToken method - TODO
    private Token scanToken () {
        // rest of method ....
        return nextToken;
    }
}
