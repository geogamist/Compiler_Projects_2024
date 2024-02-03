package com.project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CminusScanner implements Scanner{

    private BufferedReader inFile;
    private Token nextToken;
    public enum StateType {
    	START,
    	INASSIGN,
    	INCOMMENT,
    	INNUM,
    	INID,
    	DONE,
    	// rest of states ....
    }

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
    private Token scanToken () throws IOException {
        
    	int tokenStringIndex = 0;
    	Token nextToken;
    	StateType state = StateType.START;
    	boolean save;
    	
    	// Main loop
    	while (state != StateType.DONE) {
    		char c = (char)inFile.read();
    		save = true;
    		switch (state) {
    		case START:
    			break;
    		case INASSIGN:
    			break;
    		case INCOMMENT:
    			break;
    		case INNUM:
    			break;
    		case INID:
    			break;
    		case DONE:
    			break;
			default:
				break;
    		}
    	}
    	
    	
        return nextToken;
    }
}
