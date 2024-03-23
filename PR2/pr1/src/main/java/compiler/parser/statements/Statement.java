package compiler.parser.statements;

import compiler.parser.CMinusParser;
import compiler.scanner.Token.TokenType;
import compiler.parser.expressions.Expression;
public abstract class Statement {

    public static Statement parseStatement() {
        
        switch (CMinusParser.currentToken.getTokenType()) {
            case ID:
                return parseExpressionStatement();
            case NUM:
                return parseExpressionStatement();
            case LPAREN:
                return parseExpressionStatement();
            case INT:
                //TODO check if this is correct
                return parseCompoundStatement();
            case IF:
                return parseSelectionStatement();
            case WHILE:
                return parseIterationStatement();
            case RETURN:
                return parseReturnStatement();
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

    }
    public static Statement parseCompoundStatement() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case INT:
                CMinusParser.matchToken(TokenType.INT);
                CMinusParser.matchToken(TokenType.ID);
                if (CMinusParser.currentToken.getTokenType() == TokenType.LBRACKET) {
                    CMinusParser.matchToken(TokenType.LBRACKET);
                    CMinusParser.matchToken(TokenType.NUM);
                    CMinusParser.matchToken(TokenType.RBRACKET);
                }    
                parseCompoundStatement();
            case ID:
                return parseExpressionStatement();
            case NUM:
                return parseExpressionStatement();
            case LPAREN:
                return parseExpressionStatement();
            case IF:
                return parseIterationStatement();
            case WHILE:
                return parseIterationStatement();
            case RETURN:
                return parseReturnStatement();
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

    }
    public static Statement parseExpressionStatement() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case SEMI:
                CMinusParser.matchToken(TokenType.SEMI);
                break;
            case ID:
                Expression.parseExpression();
            case NUM:
                Expression.parseExpression();
            case LPAREN:
                Expression.parseExpression();
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
                
        } 
        return null;
    }
    public static Statement parseSelectionStatement() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case IF:
                CMinusParser.matchToken(TokenType.IF);
                CMinusParser.matchToken(TokenType.LPAREN);
                Expression.parseExpression();
                CMinusParser.matchToken(TokenType.RPAREN);
                Statement.parseStatement();
                if (CMinusParser.currentToken.getTokenType() == TokenType.ELSE) {
                    CMinusParser.matchToken(TokenType.ELSE);
                    Statement.parseStatement();
                }
                break;
            default:
                break;
        }
        return null;
    }
    public static Statement parseIterationStatement() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case WHILE:
                CMinusParser.matchToken(TokenType.WHILE);
                CMinusParser.matchToken(TokenType.LPAREN);
                Expression.parseExpression();
                CMinusParser.matchToken(TokenType.RPAREN);
                Statement.parseStatement();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }
        return null;
    }
    public static Statement parseReturnStatement() {
        switch (CMinusParser.currentToken.getTokenType()) {
            case RETURN:
                CMinusParser.matchToken(TokenType.RETURN);
                if (CMinusParser.currentToken.getTokenType() == TokenType.SEMI) {
                    CMinusParser.matchToken(TokenType.SEMI);
                } else {
                    Expression.parseExpression();
                    CMinusParser.matchToken(TokenType.SEMI);
                }
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }
        return null;
    }


    abstract void print();
}