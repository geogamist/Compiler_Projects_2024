package compiler.scanner;

import java.io.PushbackReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import compiler.scanner.Token.TokenType;

/** 
* This class implements the Scanner ADT, providing support for the Cminus language.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: CminusScanner.java
* Created: Feb 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
* Summary of Modifications:
*
* Description: This class provides an implementation of a scanner for the Cminus language.
* Users of this class provide an input file for be scanned, and are provided methods to 
* both view and get the next Token.
*/
public class CminusScanner implements Scanner {

    private PushbackReader inFile;
    private Token nextToken;
    private String tokenString;
    private Map<String, TokenType> keywords;

	// Keeps track of scanner states
    private enum StateType {
    	START, INEQ, INLT, INGT, INNOT, INDIV, INCOMMENT,
		INCOMMENTEXIT, INNUM, INNUMRROR, INID, DONE
    }

	/**
	 * Initializes CminusScanner for a specific file
	 * @param filename The name of the file to scan
	 */
    public CminusScanner(String filename) {
		try {
			inFile = new PushbackReader(new FileReader(filename));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Initialize keywords to look for in identifiers
        keywords = new HashMap<String, TokenType>() {{
        	put("else", TokenType.ELSE);
        	put("if", TokenType.IF);
        	put("int", TokenType.INT);
        	put("return", TokenType.RETURN);
        	put("void", TokenType.VOID);
        	put("while", TokenType.WHILE);
        }};

		// Populate the initial token
        try {
			nextToken = scanToken();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * Return the nextToken, then get a new token
	 * @return Token
	 */
    public Token getNextToken () {
        Token returnToken = nextToken;
        if (nextToken.getTokenType() != TokenType.ENDFILE) {
            try {
				nextToken = scanToken();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
        }
        return returnToken;
    }
    
	/**
	 * Return the nextToken
	 * @return Token
	 */
    public Token viewNextToken () {
        return nextToken;
    }
    
	/**
	 * Scan the input file for a singular token
	 * @return Token
	 * @throws IOException
	 */
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
    			else if (Character.isLetter(c) || c == '_') {
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
					save = false;
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
					save = false;
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
						save = false;
						state = StateType.START;
						break;
					case '*':
						break;
					// EOF indicator
    				case (char) -1:
						state = StateType.DONE;
    					currentToken.setTokenType(TokenType.ERROR);
    					break;
					default:
						save = false;
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
    			if (!Character.isDigit(c)) {
					if(Character.isLetter(c)) {
						state = StateType.INNUMRROR;
					}
					else {
						inFile.unread(c);
						save = false;
						state = StateType.DONE;
						currentToken.setTokenType(TokenType.NUM);
						currentToken.setTokenData(tokenString);
					}
    			}
    			break;
			case INNUMRROR:
				if (!Character.isLetterOrDigit(c)) {
					inFile.unread(c);
					save = false;
					state = StateType.DONE;
					currentToken.setTokenType(TokenType.ERROR);
				}
				break;
    		case INID:
    			if (!Character.isLetterOrDigit(c) && c != '_') {
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
    	
		// Clear the tokenString and return the token
		tokenString = "";
        return currentToken;
    }

    /**
     * Use identifier to see if it is a reserved word
     * @param identifier The text the token parsed out
     * @return TokenType
     */
	private TokenType reservedLookup(String identifier) {
		if (keywords.containsKey(identifier)) {
			return keywords.get(identifier);
		}
		return TokenType.ID;
	}
}
