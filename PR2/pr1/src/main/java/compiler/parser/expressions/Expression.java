package compiler.parser.expressions;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;
import compiler.scanner.Token.*;

public abstract class Expression {

    public static Expression parseExpression() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case ID:
                CMinusParser.matchToken(TokenType.ID);
                parseExpressionPrime();
                break;
            case NUM:
                CMinusParser.matchToken(TokenType.NUM);
                parseExpressionPrime();
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                parseExpression();
                CMinusParser.matchToken(TokenType.RPAREN);
                parseSimpleExpressionPrime();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }
        return null;
    };

    private static void parseExpressionPrime() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case ASSIGN:
                CMinusParser.matchToken(TokenType.ASSIGN);
                break;
            case LBRACKET:
                CMinusParser.matchToken(TokenType.LBRACKET);
                parseExpression();
                CMinusParser.matchToken(TokenType.RBRACKET);
                CMinusParser.matchToken(TokenType.ASSIGN);
                parseExpressionPrimePrime();
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                parseArgs();
                CMinusParser.matchToken(TokenType.RPAREN);
                break;
        
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }
    }

    private static void parseExpressionPrimePrime() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case ASSIGN:
                CMinusParser.matchToken(TokenType.ASSIGN);
                parseExpression();
                break;
            case TIMES:
                parseSimpleExpressionPrime();   
                break;
            case OVER:
                parseSimpleExpressionPrime();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }
    }

    private static void parseSimpleExpressionPrime() {
        parseAdditiveExpressionPrime();
        if (CMinusParser.currentToken.getTokenType() == TokenType.LT ||
            CMinusParser.currentToken.getTokenType() == TokenType.LE ||
            CMinusParser.currentToken.getTokenType() == TokenType.GT ||
            CMinusParser.currentToken.getTokenType() == TokenType.GE ||
            CMinusParser.currentToken.getTokenType() == TokenType.EQ ||
            CMinusParser.currentToken.getTokenType() == TokenType.NE) {
                CMinusParser.advanceToken();
                parseAdditiveExpression();
        }
    }
    public static Expression parseAdditiveExpression() {

        Expression lhs = parseTerm();

        while (isAddop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseTerm();
            lhs = new BinaryExpression(oldToken, lhs, rhs);
        }

        return lhs;
    }

    public static Expression parseAdditiveExpressionPrime() {

        Expression lhs = parseTermPrime();

        while (isAddop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseTerm();
            lhs = new BinaryExpression(oldToken, lhs, rhs);
        }

        return lhs;
    }
    public static Expression parseTerm() {
        Expression lhs = parseFactor();

        while (isMulop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseFactor();
            lhs = new BinaryExpression(oldToken, lhs, rhs);
        }

        return lhs;
    };

    public static Expression parseTermPrime() {
        Expression lhs = null;

        while (isMulop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseFactor();
            lhs = new BinaryExpression(oldToken, lhs, rhs);
        }

        return lhs;
    };

    public static Expression parseFactor() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case ID:
                CMinusParser.matchToken(TokenType.ID);
                parseVarcall();
                break;
            case NUM:
                CMinusParser.matchToken(TokenType.NUM);
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                parseExpression();
                CMinusParser.matchToken(TokenType.RPAREN);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }
        return null;
    }

    private static void parseVarcall() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case LBRACKET:
                CMinusParser.matchToken(TokenType.LBRACKET);
                parseExpression();
                CMinusParser.matchToken(TokenType.RBRACKET);
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                parseArgs();
                CMinusParser.matchToken(TokenType.RPAREN);
                break;
            default:
                return;
        }
    }

    private static void parseArgs() {
        parseExpression();
        while (CMinusParser.currentToken.getTokenType() == TokenType.COMMA) {
            CMinusParser.matchToken(TokenType.COMMA);
            parseExpression();
        }
    }

    //isAddop and isMulop are helper functions to determine if the current token is an addop or mulop
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
    abstract void print();

}