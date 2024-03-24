package compiler.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import compiler.parser.general.Program;
import compiler.scanner.Token;
import compiler.scanner.Token.TokenType;
import java.util.*;

public class CMinusParser implements Parser {

    static public Token currentToken;
    private static List<Token> tokens;
    private static int tokenIndex;

    CMinusParser(String filename) {

        tokens = new ArrayList<Token>();

        try (BufferedReader file = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = file.readLine()) != null) {

                // Get Token type
                Token token = new Token();
                String[] words = line.split(" ");
                switch (words[0]) {
                    case "INT":
                        token.setTokenType(TokenType.INT);
                        token.setTokenData("INT");
                        break;
                    case "VOID":
                        token.setTokenType(TokenType.VOID); 
                        token.setTokenData("VOID");
                        break;
                    case "WHILE":
                        token.setTokenType(TokenType.WHILE);
                        break;
                    case "ELSE":
                        token.setTokenType(TokenType.ELSE);
                        break;
                    case "IF":
                        token.setTokenType(TokenType.IF);
                        break;
                    case "RETURN":
                        token.setTokenType(TokenType.RETURN);
                        break;
                    case "ID":
                        token.setTokenType(TokenType.ID);
                        token.setTokenData(words[1]);
                        break;
                    case "NUM":
                        token.setTokenType(TokenType.NUM);
                        token.setTokenData(words[1]);
                        break;
                    case "ASSIGN":
                        token.setTokenType(TokenType.ASSIGN);
                        break;
                    case "EQ":
                        token.setTokenType(TokenType.EQ);
                        break;
                    case "LT":
                        token.setTokenType(TokenType.LT);
                        break;
                    case "GT":
                        token.setTokenType(TokenType.GT);
                        break;
                    case "LE":
                        token.setTokenType(TokenType.LE);
                        break;
                    case "GE":
                        token.setTokenType(TokenType.GE);
                        break;
                    case "NE":
                        token.setTokenType(TokenType.NE);
                        break;
                    case "PLUS":
                        token.setTokenType(TokenType.PLUS);
                        break;
                    case "MINUS":
                        token.setTokenType(TokenType.MINUS);
                        break;
                    case "TIMES":
                        token.setTokenType(TokenType.TIMES);
                        break;
                    case "OVER":
                        token.setTokenType(TokenType.OVER);
                        break;
                    case "LPAREN":
                        token.setTokenType(TokenType.LPAREN);
                        break;
                    case "RPAREN":
                        token.setTokenType(TokenType.RPAREN);
                        break;
                    case "LBRACKET":
                        token.setTokenType(TokenType.LBRACKET);
                        break;
                    case "RBRACKET":
                        token.setTokenType(TokenType.RBRACKET);
                        break;
                    case "LBRACE":
                        token.setTokenType(TokenType.LBRACE);
                        break;
                    case "RBRACE":
                        token.setTokenType(TokenType.RBRACE);
                        break;
                    case "SEMI":
                        token.setTokenType(TokenType.SEMI);
                        break;
                    case "COMMA":
                        token.setTokenType(TokenType.COMMA);
                        break;
                    default:
                        break;
                }
                tokens.add(token);
            }
        } 
        catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        currentToken = tokens.getFirst();
        tokenIndex = 0;
    }

    public static Object matchToken(TokenType type) {
        if (currentToken.getTokenType() != type) {
            throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }
        Object data = advanceToken().getTokenData();
        return data;
    };

    public static Token advanceToken() {
        Token token = currentToken;
        currentToken = tokens.get(++tokenIndex);
        return token;
    };

    public Program parse() {
        Program ast = new Program();
        return ast;
    };

    public void printTree() {};
}