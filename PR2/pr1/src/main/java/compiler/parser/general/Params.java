package compiler.parser.general;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;

public class Params {
    
    public Params lhs;
    public Params rhs;
    public Params() {};
    
    
    public static Params parseParams() {
        
        switch (CMinusParser.currentToken.getTokenType()) {
            case VOID:
                CMinusParser.matchToken(Token.TokenType.VOID);
                break;
            case INT:
                
                parseParam();
                while (CMinusParser.currentToken.getTokenType() == Token.TokenType.COMMA) {
                    CMinusParser.matchToken(Token.TokenType.COMMA);
                    parseParam();
                }
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());

        }
        return null;
    }

    public static Params parseParam(){
        CMinusParser.matchToken(Token.TokenType.INT);
        CMinusParser.matchToken(Token.TokenType.ID);
        if (CMinusParser.currentToken.getTokenType() == Token.TokenType.LBRACKET) {
            CMinusParser.matchToken(Token.TokenType.LBRACKET);
            CMinusParser.matchToken(Token.TokenType.RBRACKET);
        }
        //TODO: make sure this is correct
        return new Params();
        
    }
    
    void print() {};
}
