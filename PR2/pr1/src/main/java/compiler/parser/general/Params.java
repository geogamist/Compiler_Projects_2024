package compiler.parser.general;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;
import compiler.parser.expressions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Params {

    public List<Expression> params;
    public String type;

    public Params(String type, List<Expression> params) {
        this.type = type;
        this.params = params;
    };
    
    public static Params parseParams() {

        String type = null;
        List<Expression> params = null;
        Expression param = null;
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

    public static Expression parseParam() {

        Expression numericExpression = null;
        Expression param = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case INT:
                String type = (String)CMinusParser.matchToken(Token.TokenType.INT);
                String identifier = (String)CMinusParser.matchToken(Token.TokenType.ID);
                if (CMinusParser.currentToken.getTokenType() == Token.TokenType.LBRACKET) {
                    CMinusParser.matchToken(Token.TokenType.LBRACKET);
                    numericExpression = new NumericExpression("0");
                    CMinusParser.matchToken(Token.TokenType.RBRACKET);
                    param = new IdentifierExpression(identifier, numericExpression, type);
                }
                else {
                    param = new IdentifierExpression(identifier, type);
                }
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return param;
    }
    
    public void print(FileWriter file) throws IOException {
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                //file.write(type + " ");
                params.get(i).print(file);
                if ((i + 1) < params.size()) {
                    file.write(", ");
                }
            }
        }
    };
}
