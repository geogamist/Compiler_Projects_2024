package compiler.parser;

import compiler.parser.general.Program;
import compiler.scanner.Token;
import compiler.scanner.Token.TokenType;

public class CMinusParser implements Parser {

    static public Token currentToken;

    CMinusParser() {
        // Open file and get tokens
        currentToken = new Token();
    }

    public static boolean matchToken(TokenType type) {
        if (currentToken.getTokenType() == type) {
            advanceToken();
            return true;
        }
        return false;
    };

    public static Token advanceToken() {
        Token token = currentToken;
        // Get next token from file

        return token;
    };

    public static Object createBinaryExpression(TokenType type, Object lhs, Object rhs) {
        return null;
    }

    public static Object createNumericExpression(TokenType type, Object lhs, Object rhs) {
        return null;
    }

    public Program parse() {
        Program ast = new Program();
        return ast;
    };

    public void printTree() {

    };
}