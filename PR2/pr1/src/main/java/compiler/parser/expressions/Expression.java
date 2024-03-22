package compiler.parser.expressions;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;
import compiler.scanner.Token.*;

public abstract class Expression {

    

    public static Expression parseFactor() {
        /*
        switch (CMinusParser.currentToken.getTokenType()) {
            case Token.LPAREN_TOKEN:
                advanceToken();
                Expression returnExpr = parseExpression ();
                matchToken(Token.RPAREN_TOKEN);
                return returnExpr;
                break;
            case Token.IDENT_TOKEN:
                Token oldToken = advanceToken();
                return createIdentExpr(oldToken);
                break;
            case Token.NUM_TOKEN:
               Token oldToken = advanceToken();
                return createNumExpr(oldToken);
                break;
            default:
                logParseError();
                return null;
        }
        */
        return null;
    };

    public static Expression parseTerm() {
        Expression lhs = parseFactor();

        while (isMulop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseFactor();
            lhs = BinaryExpression(oldToken, lhs, rhs);
        }

        return lhs;
    };

    public static Expression parseTermPrime() {
        Expression lhs = null;

        while (isMulop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseFactor();
            lhs = BinaryExpression(oldToken, lhs, rhs);
        }

        return lhs;
    };

    public static Expression parseAdditiveExpression() {

        Expression lhs = parseTerm();

        while (isAddop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseTerm();
            lhs = BinaryExpression(oldToken, lhs, rhs);
        }

        return lhs;
    }

    public static Expression parseAdditiveExpressionPrime() {

        Expression lhs = parseTermPrime();

        while (isAddop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseTerm();
            lhs = BinaryExpression(oldToken, lhs, rhs);
        }

        return lhs;
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