package compiler.parser.expressions;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;
import compiler.scanner.Token.*;

public abstract class Expression {

    private TokenType type;
    private Object lhs;
    private Object rhs;

    public static Expression parseFactor() {
        return null;
    };

    public static Expression parseTerm() {
        Expression lhs = parseFactor();

        while (isMulop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseFactor();
            lhs = createBinopExpression(oldToken, lhs, rhs);
        }

        return lhs;
    };

    public static Expression parseTermPrime() {
        Expression lhs = null;

        while (isMulop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseFactor();
            lhs = createBinopExpression(oldToken, lhs, rhs);
        }

        return lhs;
    };

    public static Expression createBinopExpression(Token type, Expression lhs, Expression rhs) {
        return null;
    }

    public static Expression createNumExpression(Token type) {
        return null;
    }

    abstract void print();

    public static boolean isAddop(TokenType type) {
        if (CMinusParser.currentToken.getTokenType() == TokenType.PLUS ||
            CMinusParser.currentToken.getTokenType() == TokenType.MINUS) {
                return true;
        }
        return false;
    }

    public static boolean isMulop(TokenType type) {
        if (CMinusParser.currentToken.getTokenType() == TokenType.TIMES ||
            CMinusParser.currentToken.getTokenType() == TokenType.OVER) {
                return true;
        }
        return false;
    }
}