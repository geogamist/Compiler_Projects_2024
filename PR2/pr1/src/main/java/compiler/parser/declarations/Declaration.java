package compiler.parser.declarations;
import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.CMinusParser;
import compiler.parser.expressions.Expression;
import compiler.parser.expressions.IdentifierExpression;
import compiler.parser.expressions.NumericExpression;
import compiler.scanner.Token;
import compiler.scanner.Token.TokenType;
import compiler.parser.general.Params;
import compiler.parser.statements.Statement;

/** 
* This class implements the Declaration abstract class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: Declaration.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides an abstract class for declarations. Users
* of this class are provided methods to parse declarations.
*/

public abstract class Declaration {

    public Expression identifierExpression;
    public String type;

    public Declaration() {
        identifierExpression = null;
        type = null;
    }

    public static Declaration parseDeclaration() {

        Declaration declaration = null;
        Expression identifierExpression = null;
        String identifier = null;
        String type = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case VOID:
                type = (String)CMinusParser.matchToken(TokenType.VOID);
                identifier = (String)CMinusParser.matchToken(TokenType.ID);
                identifierExpression = new IdentifierExpression(identifier, type);
                declaration = parseFunctionDeclaration();
                declaration.intialize(type, identifierExpression);
                break;
            case INT:
                type = (String)CMinusParser.matchToken(TokenType.INT);
                identifier = (String)CMinusParser.matchToken(TokenType.ID);
                identifierExpression = new IdentifierExpression(identifier, type);
                declaration = parseDeclarationPrime(identifierExpression);
                declaration.intialize(type, declaration.identifierExpression);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value parseDeclaration: " + CMinusParser.currentToken.getTokenType());
        }

        return declaration;
    };

    public static Declaration parseFunctionDeclaration() {

        Declaration declaration = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                Params params = Params.parseParams();
                CMinusParser.matchToken(TokenType.RPAREN);
                Statement compoundStatement = Statement.parseCompoundStatement();
                declaration = new FunctionDeclaration(params, compoundStatement);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value parseFunctionDeclaration: " + CMinusParser.currentToken.getTokenType());
        }

        return declaration;
    }
    
    public static Declaration parseDeclarationPrime(Expression lhs) {

        Declaration declaration = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case SEMI:
                CMinusParser.matchToken(Token.TokenType.SEMI);
                declaration = new VariableDeclaration(lhs);
                break;
            case LBRACKET:
                CMinusParser.matchToken(Token.TokenType.LBRACKET);
                String number = (String)CMinusParser.matchToken(Token.TokenType.NUM);
                NumericExpression numericExpression = new NumericExpression(number);
                Expression identifierExpression = new IdentifierExpression(((IdentifierExpression)lhs).getIdentifier(), numericExpression, ((IdentifierExpression)lhs).type);
                declaration = new VariableDeclaration(identifierExpression);
                CMinusParser.matchToken(Token.TokenType.RBRACKET);
                CMinusParser.matchToken(Token.TokenType.SEMI);
                break;
            case LPAREN:
                declaration = parseFunctionDeclaration();
                declaration.identifierExpression = lhs;
                break;
            default:
                throw new IllegalArgumentException("Unexpected value parseDeclarationPrime: " + CMinusParser.currentToken.getTokenType());
        }

        return declaration;
    }

    private void intialize(String type, Expression identifierExpression) {
        this.type = type;
        this.identifierExpression = identifierExpression;
    }

    public abstract void print(FileWriter file, int spacer) throws IOException;
}
