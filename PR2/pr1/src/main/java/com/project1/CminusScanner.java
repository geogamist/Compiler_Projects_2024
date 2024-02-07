package com.project1;

import java.io.PushbackReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.project1.Token.TokenType;

public class CminusScanner implements Scanner {

    private PushbackReader inFile;
    private Token nextToken;
    private String tokenString;
    Map<String, TokenType> keywords;
    private enum StateType {
    	START,
    	INEQ,
    	INLT,
    	INGT,
    	INNOT,
    	INDIV,
    	INCOMMENT,
    	INCOMMENTEXIT,
    	INNUM,
    	INID,
    	DONE
    }

    public CminusScanner(String filename) {
		try {
			CMinusScanner(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void CMinusScanner (String filename) throws FileNotFoundException {
        inFile = new PushbackReader(new FileReader(filename));
        keywords = new HashMap<String, TokenType>() {{
        	put("else", TokenType.ELSE);
        	put("if", TokenType.IF);
        	put("int", TokenType.INT);
        	put("return", TokenType.RETURN);
        	put("void", TokenType.VOID);
        	put("while", TokenType.WHILE);
        }};
        tokenString = "";
        try {
			nextToken = scanToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public Token getNextToken () {
        Token returnToken = nextToken;
        if (nextToken.getTokenType() != TokenType.ENDFILE) {
            try {
				nextToken = scanToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return returnToken;
    }
    
    public Token viewNextToken () {
        return nextToken;
    }
    
    //scanToken method - TODO
    private Token scanToken () throws IOException {
        
    	Token currentToken = new Token();
		tokenString = "";
    	StateType state = StateType.START;
    	boolean save;
    	
    	// Main loop
    	while (state != StateType.DONE) {
    		char c = (char)inFile.read();
    		save = true;
    		switch (state) {
    		case START:
    			if (Character.isDigit(c)) {
    				state = StateType.INNUM;
    			}
    			else if (Character.isLetter(c)) {
    				state = StateType.INID;
    			}
    			else if (c == '=') {
    				state = StateType.INEQ;
    			}
    			else if (c == '<') {
    				state = StateType.INLT;
    			}
    			else if (c == '>') {
    				state = StateType.INGT;
    			}
    			else if (c == '!') {
    				state = StateType.INNOT;
    			}
    			else if ((c == ' ') || (c == '\t') || (c == '\n')) {
    				save = false;
    			}
    			// In a comment
    			else if (c == '/') {
    				state = StateType.INDIV;
    			}
    			else {
    				state = StateType.DONE;
    				switch (c) {
    				// EOF indicator
    				case (char) -1:
    					save = false;
    					currentToken.setTokenType(TokenType.ENDFILE);
    					break;
    				case '=':
    					currentToken.setTokenType(TokenType.EQ);
    					break;
    				case '<':
    					currentToken.setTokenType(TokenType.LT);
    					break;
    				case '+':
    					currentToken.setTokenType(TokenType.PLUS);
    					break;
    				case '-':
    					currentToken.setTokenType(TokenType.MINUS);
    					break;
    				case '*':
    					currentToken.setTokenType(TokenType.TIMES);
    					break;
    				case '(':
    					currentToken.setTokenType(TokenType.LPAREN);
    					break;
    				case ')':
    					currentToken.setTokenType(TokenType.RPAREN);
    					break;
    				case '{':
    					currentToken.setTokenType(TokenType.LBRACE);
    					break;
    				case '}':
    					currentToken.setTokenType(TokenType.RBRACE);
    					break;
    				case '[':
    					currentToken.setTokenType(TokenType.LBRACKET);
    					break;
    				case ']':
    					currentToken.setTokenType(TokenType.RBRACKET);
    					break;
    				case ';':
    					currentToken.setTokenType(TokenType.SEMI);
    					break;
    				case ',':
    					currentToken.setTokenType(TokenType.COMMA);
    					break;
					// End of line indicator
					case '\r':
						break;
    				default:
    					currentToken.setTokenType(TokenType.ERROR);
    					break;
    			}}
    			break;
    		case INDIV:
    			if (c == '*') {
    				state = StateType.INCOMMENT;
    			}
    			else {
    				state = StateType.DONE;
    				inFile.unread(c);
					currentToken.setTokenType(TokenType.OVER);
    			}
    			break;
    		case INCOMMENT:
    			save = false;
				switch (c) {
				case '*':
					state = StateType.INCOMMENTEXIT;
					break;
				// EOF indicator
				case (char) -1:
					state = StateType.DONE;
					currentToken.setTokenType(TokenType.ERROR);
					break;
    			default:
    				break;
				}
				break;
    		case INCOMMENTEXIT:
				switch (c) {
					case '/':
						state = StateType.START;
						break;
					// EOF indicator
    				case (char) -1:
						state = StateType.DONE;
    					currentToken.setTokenType(TokenType.ERROR);
    					break;
					default:
						state = StateType.INCOMMENT;
						break;
				}
    			break;
    		case INEQ:
    			state = StateType.DONE;
    			if (c == '=') {
    				currentToken.setTokenType(TokenType.EQ);
    			}
    			else {
    				inFile.unread(c);
    				save = false;
    				currentToken.setTokenType(TokenType.ASSIGN);
    			}
    			break;
    		case INLT:
    			state = StateType.DONE;
    			if (c == '=') {
    				currentToken.setTokenType(TokenType.LE);
    			}
    			else {
    				inFile.unread(c);
    				save = false;
    				currentToken.setTokenType(TokenType.LT);
    			}
    			break;
    		case INGT:
    			state = StateType.DONE;
    			if (c == '=') {
    				currentToken.setTokenType(TokenType.GE);
    			}
    			else {
    				inFile.unread(c);
    				save = false;
    				currentToken.setTokenType(TokenType.GT);
    			}
    			break;
    		case INNOT:
    			state = StateType.DONE;
    			if (c == '=') {
    				currentToken.setTokenType(TokenType.NE);
    			}
    			else {
    				inFile.unread(c);
    				save = false;
    				currentToken.setTokenType(TokenType.ERROR);
    			}
    			break;
    		case INNUM:
				/* 
				 * TODO: Currently, this will back up the input and produce a valud digit on
				 * something like this:
				 * 		int 1num;
				 * producing
				 * 		INT
				 * 		NUM 1
				 * 		ID num
				 * 		SEMI
				 * Do we want to throw an error on all of input like "1num"?
				 * Note: IDs don't care about this issue
				 */
    			if (!Character.isDigit(c)) {
    				inFile.unread(c);
    				save = false;
    				state = StateType.DONE;
    				currentToken.setTokenType(TokenType.NUM);
    				currentToken.setTokenData(tokenString);
    			}
    			break;
    		case INID:
    			if (!Character.isLetterOrDigit(c)) {
    				inFile.unread(c);
    				save = false;
    				state = StateType.DONE;
    				currentToken.setTokenType(TokenType.ID);
    				currentToken.setTokenData(tokenString);
    			}
    			break;
    		case DONE:
    			break;
			default:
				System.err.println("Scaner bug: state= " + state);
				state = StateType.DONE;
				currentToken.setTokenType(TokenType.ERROR);
				break;
    		}
    		if (save) {
        		tokenString += c;
        	}
    		if (state.equals(StateType.DONE)) {
    			if (currentToken.getTokenType() == TokenType.ID) {
    				currentToken.setTokenType(reservedLookup(tokenString));
    			}
				tokenString += '\0';
    		}
    	}
    	
        return currentToken;
    }

    /**
     * USes identifier to see if it is a reserved word
     * @param identifier
     * @return
     */
	private TokenType reservedLookup(String identifier) {
		if (keywords.containsKey(identifier)) {
			return keywords.get(identifier);
		}
		return TokenType.ID;
	}
}
