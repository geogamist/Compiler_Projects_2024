package compiler.parser.expressions;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;
import compiler.scanner.Token.*;

public abstract class Expression {

    private String value;

    public Expression() {
        this.value = null;
    }

    public static Expression parseExpression() {

        String value = null;
        Expression expression = null;
        Expression simpleExpressionPrime = null;
        Expression returnExpression = null;
        Expression numericExpression = null;
        Expression identifierExpression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case ID:
                value = (String)CMinusParser.matchToken(TokenType.ID);
                identifierExpression = new IdentifierExpression(value, null);
                expression = parseExpressionPrime();
                returnExpression = new BinaryExpression(null, identifierExpression, expression);
                break;
            case NUM:
                value = (String)CMinusParser.matchToken(TokenType.NUM);
                numericExpression = new NumericExpression(value);
                simpleExpressionPrime = parseSimpleExpressionPrime();
                returnExpression = new BinaryExpression(null, numericExpression, simpleExpressionPrime);
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                expression = parseExpression();
                CMinusParser.matchToken(TokenType.RPAREN);
                simpleExpressionPrime = parseSimpleExpressionPrime();
                returnExpression = new BinaryExpression(null, expression, simpleExpressionPrime);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return returnExpression;
    };

    private static Expression parseExpressionPrime() {

        Expression returnExpression = null;
        Expression expression = null;
        Expression expressionPrimePrime = null;
        Expression args = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case ASSIGN:
                CMinusParser.matchToken(TokenType.ASSIGN);
                expression = parseExpression();
                returnExpression = new AssignExpression(expression);
                break;
            case LBRACKET:
                CMinusParser.matchToken(TokenType.LBRACKET);
                expression = parseExpression();
                CMinusParser.matchToken(TokenType.RBRACKET);
                expressionPrimePrime = parseExpressionPrimePrime();
                returnExpression = new BinaryExpression(null, expression, expressionPrimePrime);
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                args = parseArgs();
                CMinusParser.matchToken(TokenType.RPAREN);
                expression = parseSimpleExpressionPrime();
                returnExpression = new BinaryExpression(null, args, expression);
                break;
            case TIMES:
            case OVER:
                returnExpression = parseSimpleExpressionPrime();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return returnExpression;
    }

    private static Expression parseExpressionPrimePrime() {

        Expression returnExpression = null;
        Expression expression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case ASSIGN:
                CMinusParser.matchToken(TokenType.ASSIGN);
                expression = parseExpression();
                returnExpression = new AssignExpression(expression);
                break;
            case TIMES:
            case OVER:
                returnExpression = parseSimpleExpressionPrime();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return returnExpression;
    }

    private static Expression parseSimpleExpressionPrime() {

        Expression returnExpression = null;
        Expression additiveExpression = null;

        returnExpression = parseAdditiveExpressionPrime();
        if (isRelop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            additiveExpression = parseAdditiveExpression();
            returnExpression = new BinaryExpression(oldToken, returnExpression, additiveExpression);
        }

        return returnExpression;
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

        String value = null;
        Expression returnExpression = null;
        Expression identifierExpression = null;
        Expression expression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case ID:
                value = (String)CMinusParser.matchToken(TokenType.ID);
                identifierExpression = new IdentifierExpression(value, null);
                expression = parseVarcall();
                returnExpression = new CallExpression(identifierExpression, expression);
                break;
            case NUM:
                value = (String)CMinusParser.matchToken(TokenType.NUM);
                returnExpression = new NumericExpression(value);
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                returnExpression = parseExpression();
                CMinusParser.matchToken(TokenType.RPAREN);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return returnExpression;
    }

    private static Expression parseVarcall() {

        Expression returnExpression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case LBRACKET:
                CMinusParser.matchToken(TokenType.LBRACKET);
                returnExpression = parseExpression();
                CMinusParser.matchToken(TokenType.RBRACKET);
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                returnExpression = parseArgs();
                CMinusParser.matchToken(TokenType.RPAREN);
                break;
            default:
                break;
        }

        return returnExpression;
    }

    private static Expression parseArgs() {

        Expression lhs = parseExpression();

        while (CMinusParser.currentToken.getTokenType() == TokenType.COMMA) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseExpression();
            lhs = new BinaryExpression(oldToken, lhs, rhs);
        }

        return lhs;
    }

    public void initialize(String value) {
        this.value = value;
    }

    //isAddop, isMulop, and isRelop are helper functions to determine if the current token is an addop, mulop, or relop
    public static boolean isRelop(TokenType type) {
        if (CMinusParser.currentToken.getTokenType() == TokenType.LT ||
            CMinusParser.currentToken.getTokenType() == TokenType.LE ||
            CMinusParser.currentToken.getTokenType() == TokenType.GT ||
            CMinusParser.currentToken.getTokenType() == TokenType.GE ||
            CMinusParser.currentToken.getTokenType() == TokenType.EQ ||
            CMinusParser.currentToken.getTokenType() == TokenType.NE) {
                return true;
        }
        return false;
    }

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