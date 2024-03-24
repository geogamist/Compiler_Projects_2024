package compiler.parser.general;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;
import compiler.parser.expressions.*;

public class Param {
    
    public Param lhs;
    public Param rhs;
    public String type;
    public Expression identifierExpression;

    public Param(Param lhs, Param rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    };
    
    public static Param parseParams() {

        String type = null;
        Param lhs = null;
        Param rhs = null;
        
        switch (CMinusParser.currentToken.getTokenType()) {
            case VOID:
                type = (String)CMinusParser.matchToken(Token.TokenType.VOID);
                lhs = new Param(null, null);
                lhs.intialize(type, null);
                break;
            case INT:
                lhs = parseParam();
                while (CMinusParser.currentToken.getTokenType() == Token.TokenType.COMMA) {
                    CMinusParser.matchToken(Token.TokenType.COMMA);
                    rhs = parseParam();
                    lhs = new Param(lhs, rhs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return lhs;
    }

    public static Param parseParam() {

        String type = null;
        String identifier = null;
        Expression identifierExpression = null;
        Param param = null;

        type = (String)CMinusParser.matchToken(Token.TokenType.INT);
        identifier = (String)CMinusParser.matchToken(Token.TokenType.ID);
        
        if (CMinusParser.currentToken.getTokenType() == Token.TokenType.LBRACKET) {
            CMinusParser.matchToken(Token.TokenType.LBRACKET);
            CMinusParser.matchToken(Token.TokenType.RBRACKET);
        }
        identifierExpression = new IdentifierExpression(identifier, null);
        
        param = new Param(null, null);
        param.intialize(type, identifierExpression);
        return param;
        
    }

    private void intialize(String type, Expression identifierExpression) {
        this.type = type;
        this.identifierExpression = identifierExpression;
    }
    
    void print() {};
}
