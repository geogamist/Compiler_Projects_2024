package compiler.parser.expressions;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;
import compiler.scanner.Token.*;
import java.util.*;

public abstract class Expression {

    public static Expression parseExpression() {

        Expression returnExpression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case ID:
                String identifier = (String)CMinusParser.matchToken(TokenType.ID);
                Expression identifierExpression = new IdentifierExpression(identifier);
                returnExpression = parseExpressionPrime(identifierExpression);
                break;
            case NUM:
                String number = (String)CMinusParser.matchToken(TokenType.NUM);
                Expression numericExpression = new NumericExpression(number);
                returnExpression = parseSimpleExpressionPrime(numericExpression);
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                Expression expression = parseExpression();
                CMinusParser.matchToken(TokenType.RPAREN);
                returnExpression = parseSimpleExpressionPrime(expression);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return returnExpression;
    };

    private static Expression parseExpressionPrime(Expression lhs) {

        Expression returnExpression = null;
        Expression expression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case ASSIGN:
                CMinusParser.matchToken(TokenType.ASSIGN);
                expression = parseExpression();
                returnExpression = new AssignExpression(lhs, expression);
                break;
            case LBRACKET:
                CMinusParser.matchToken(TokenType.LBRACKET);
                expression = parseExpression();
                CMinusParser.matchToken(TokenType.RBRACKET);
                Expression identifierExpression = new IdentifierExpression(((IdentifierExpression)lhs).getIdentifier(), expression);
                returnExpression = parseExpressionPrimePrime(identifierExpression);
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                List<Expression> args = parseArgs();
                CMinusParser.matchToken(TokenType.RPAREN);
                Expression callExpression = new CallExpression(lhs, args);
                returnExpression = parseSimpleExpressionPrime(callExpression);
                break;
            case TIMES:
            case OVER:
                returnExpression = parseSimpleExpressionPrime(lhs);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return returnExpression;
    }

    private static Expression parseExpressionPrimePrime(Expression lhs) {

        Expression returnExpression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case ASSIGN:
                CMinusParser.matchToken(TokenType.ASSIGN);
                Expression expression = parseExpression();
                returnExpression = new AssignExpression(lhs, expression);
                break;
            case TIMES:
            case OVER:
                returnExpression = parseSimpleExpressionPrime(lhs);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return returnExpression;
    }

    private static Expression parseSimpleExpressionPrime(Expression lhs) {

        Expression returnExpression = null;

        returnExpression = parseAdditiveExpressionPrime(lhs);
        if (isRelop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression additiveExpression = parseAdditiveExpression();
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

    public static Expression parseAdditiveExpressionPrime(Expression lhs) {

        Expression left = parseTermPrime(lhs);

        while (isAddop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseTerm();
            left = new BinaryExpression(oldToken, left, rhs);
        }

        return left;
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

    public static Expression parseTermPrime(Expression lhs) {

        Expression left = lhs;

        while (isMulop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseFactor();
            left = new BinaryExpression(oldToken, left, rhs);
        }

        return left;
    };

    public static Expression parseFactor() {

        Expression returnExpression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case ID:
                String identifier = (String)CMinusParser.matchToken(TokenType.ID);
                Expression identifierExpression = new IdentifierExpression(identifier);
                returnExpression = parseVarcall(identifierExpression);
                break;
            case NUM:
                String number = (String)CMinusParser.matchToken(TokenType.NUM);
                returnExpression = new NumericExpression(number);
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

    private static Expression parseVarcall(Expression lhs) {

        Expression returnExpression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case LBRACKET:
                CMinusParser.matchToken(TokenType.LBRACKET);
                Expression capacity = parseExpression();
                returnExpression = new IdentifierExpression(((IdentifierExpression)lhs).getIdentifier(), capacity);
                CMinusParser.matchToken(TokenType.RBRACKET);
                break;
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                List<Expression> args = parseArgs();
                returnExpression = new CallExpression(lhs, args);
                CMinusParser.matchToken(TokenType.RPAREN);
                break;
            default:
                break;
        }

        return returnExpression;
    }

    private static List<Expression> parseArgs() {

        List<Expression> args = new ArrayList<>();
        Expression arg = parseExpression();
        args.add(arg);

        while (CMinusParser.currentToken.getTokenType() == TokenType.COMMA) {
            CMinusParser.advanceToken();
            arg = parseExpression();
            args.add(arg);
        }

        return args;
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