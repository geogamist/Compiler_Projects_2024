package compiler.parser.statements;

import compiler.parser.CMinusParser;
import compiler.scanner.Token.TokenType;
import compiler.parser.expressions.Expression;
import compiler.parser.expressions.IdentifierExpression;
import compiler.parser.expressions.NumericExpression;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/** 
* This class implements the Statement abstract class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: Statement.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides an abstract class for parsing statements. 
* It is meant to be called though the root Program class though its own 
* realated parse methods.
*/

public abstract class Statement {

    public static Statement parseStatement() {

        Statement statement = null;
        switch (CMinusParser.currentToken.getTokenType()) {
            //NO BREAKS all lead to same case
            case ID:
            //make case for func
            case NUM:
            case LPAREN:
            case SEMI:
                statement = parseExpressionStatement();
                break;
            case LBRACE:
                statement = parseCompoundStatement();
                break;
            case IF:
                statement = parseSelectionStatement();
                break;
            case WHILE:
                statement = parseIterationStatement();
                break;
            case RETURN:
                statement = parseReturnStatement();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value parseStatement: " + CMinusParser.currentToken.getTokenType());
        }

        return statement;
    }

    public static Statement parseCompoundStatement() {

        Statement returnStatement = null;
        //parse compound statement
        switch (CMinusParser.currentToken.getTokenType()) {
            case LBRACE:
                
                //parse compound statement
                CMinusParser.matchToken(TokenType.LBRACE);
                List<Expression> localDeclarations = new ArrayList<Expression>();
                List<Statement> statements = new ArrayList<Statement>();
                Statement statement = null;

                // Parse local declarations may be non or many 
                while (CMinusParser.currentToken.getTokenType() == TokenType.INT) {
        
                    Expression identifierExpression = null;
                    Expression numericExpression = null;
        
                    String value = (String)CMinusParser.matchToken(TokenType.INT);
                    String identifier = (String)CMinusParser.matchToken(TokenType.ID);
                    
                    if (CMinusParser.currentToken.getTokenType() == TokenType.LBRACKET) {
                        CMinusParser.matchToken(TokenType.LBRACKET);
                        String number = (String)CMinusParser.matchToken(TokenType.NUM);
                        numericExpression = new NumericExpression(number);
                        CMinusParser.matchToken(TokenType.RBRACKET);
                    }
                    CMinusParser.matchToken(TokenType.SEMI);
        
                    //identifierExpression = new IdentifierExpression(identifier, numericExpression, value);
                    identifierExpression = new IdentifierExpression(identifier, value);
                    localDeclarations.add(identifierExpression);
                }
        
                // Parse statements may be non or many 
                while (CMinusParser.currentToken.getTokenType() == TokenType.ID ||
                    CMinusParser.currentToken.getTokenType() == TokenType.NUM ||
                    CMinusParser.currentToken.getTokenType() == TokenType.LPAREN ||
                    CMinusParser.currentToken.getTokenType() == TokenType.SEMI ||
                    CMinusParser.currentToken.getTokenType() == TokenType.LBRACE ||
                    CMinusParser.currentToken.getTokenType() == TokenType.IF ||
                    CMinusParser.currentToken.getTokenType() == TokenType.WHILE ||
                    CMinusParser.currentToken.getTokenType() == TokenType.RETURN
                ) {
                    statement = parseStatement();
                    statements.add(statement);
                }
        
                CMinusParser.matchToken(TokenType.RBRACE);
                returnStatement = new CompoundStatement(localDeclarations, statements);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value parseCompoundStatement: " + CMinusParser.currentToken.getTokenType());
        }

        return returnStatement;
    }

    public static Statement parseExpressionStatement() {

        Statement statement = null;
        //parse expression statement
        switch (CMinusParser.currentToken.getTokenType()) {
            case ID:
            case NUM:
            case LPAREN:
                Expression expression = Expression.parseExpression();
                statement = new ExpressionStatement(expression);
            case SEMI:
                CMinusParser.matchToken(TokenType.SEMI);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value parseExpressionStatement: " + CMinusParser.currentToken.getTokenType());
        }

        return statement;
    }

    public static Statement parseSelectionStatement() {

        Statement returnStatement = null;
        Statement elseStatement = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case IF:
                CMinusParser.matchToken(TokenType.IF);
                CMinusParser.matchToken(TokenType.LPAREN);
                Expression expression = Expression.parseExpression();
                CMinusParser.matchToken(TokenType.RPAREN);
                Statement thenStatement = Statement.parseStatement();
                if (CMinusParser.currentToken.getTokenType() == TokenType.ELSE) {
                    CMinusParser.matchToken(TokenType.ELSE);
                    elseStatement = Statement.parseStatement();
                }
                returnStatement = new SelectionStatement(expression, thenStatement, elseStatement);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value parseSelectionStatement: " + CMinusParser.currentToken.getTokenType());
        }

        return returnStatement;
    }

    public static Statement parseIterationStatement() {

        Statement returnStatement = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case WHILE:
                CMinusParser.matchToken(TokenType.WHILE);
                CMinusParser.matchToken(TokenType.LPAREN);
                Expression expression = Expression.parseExpression();
                CMinusParser.matchToken(TokenType.RPAREN);
                Statement statement = Statement.parseStatement();
                returnStatement = new IterationStatement(expression, statement);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value parseIterationStatement: " + CMinusParser.currentToken.getTokenType());
        }

        return returnStatement;
    }

    public static Statement parseReturnStatement() {

        Statement returnStatement = null;
        Expression expression = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case RETURN:
                CMinusParser.matchToken(TokenType.RETURN);
                if (CMinusParser.currentToken.getTokenType() != TokenType.SEMI) {
                    expression = Expression.parseExpression();
                }
                CMinusParser.matchToken(TokenType.SEMI);
                returnStatement = new ReturnStatement(expression);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value parseReturnStatement: " + CMinusParser.currentToken.getTokenType());
        }

        return returnStatement;
    }

    public abstract void print(FileWriter file, int spacer) throws IOException;
}