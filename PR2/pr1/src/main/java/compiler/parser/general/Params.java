package compiler.parser.general;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;
import compiler.parser.expressions.*;
import java.util.*;

public class Params {

    /**
     * Params: 
     * Expression identifierExpression,
     * Expression numericExpression
     */
    public List<Object[]> params;
    public String type;

    public Params(String type, List<Object[]> params) {
        this.type = type;
        this.params = params;
    };
    
    public static Params parseParams() {

        String type = null;
        List<Object[]> params = null;
        Object[] param = null;
        Params returnParams = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case VOID:
                type = (String)CMinusParser.matchToken(Token.TokenType.VOID);
                returnParams = new Params(type, params);
                break;
            case INT:
                type = (String)CMinusParser.currentToken.getTokenData();
                params = new ArrayList<>();
                param = parseParam();
                params.add(param);
                while (CMinusParser.currentToken.getTokenType() == Token.TokenType.COMMA) {
                    CMinusParser.matchToken(Token.TokenType.COMMA);
                    param = parseParam();
                    params.add(param);
                }
                returnParams = new Params(type, params);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return returnParams;
    }

    public static Object[] parseParam() {

        Expression numericExpression = null;

        String type = (String)CMinusParser.matchToken(Token.TokenType.INT);
        String identifier = (String)CMinusParser.matchToken(Token.TokenType.ID);
        if (CMinusParser.currentToken.getTokenType() == Token.TokenType.LBRACKET) {
            CMinusParser.matchToken(Token.TokenType.LBRACKET);
            numericExpression = new NumericExpression(null);
            CMinusParser.matchToken(Token.TokenType.RBRACKET);
        }
        Expression identifierExpression = new IdentifierExpression(identifier);
        Object[] param = new Object[]{identifierExpression, numericExpression};
        return param;
    }

    private String getParam(List<Object[]> params, int id) {
        IdentifierExpression identifierExpression = (IdentifierExpression)params.get(id)[0];
        NumericExpression numericExpression = (NumericExpression)params.get(id)[1];
        return identifierExpression.getIdentifier() + numericExpression.getValue();
    }
    
    void print() {};
}
