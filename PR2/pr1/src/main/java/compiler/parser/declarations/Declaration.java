package compiler.parser.declarations;
import compiler.parser.CMinusParser;
import compiler.scanner.Token;
import compiler.scanner.Token.TokenType;
import compiler.parser.general.Params;
import compiler.parser.statements.Statement;
public abstract class Declaration {
    
    Declaration() {};

    public static Declaration parseDeclaration() {
        Declaration decl = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case VOID:
                CMinusParser.matchToken(TokenType.VOID);
                CMinusParser.matchToken(TokenType.ID);
                decl = parseFunctionDeclaration();
                break;
            case INT:
                CMinusParser.matchToken(TokenType.INT);
                CMinusParser.matchToken(TokenType.ID);
                decl = parseDeclarationPrime();
                break;
        
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }
        return null;
    };

    public static Declaration parseFunctionDeclaration() {

        switch (CMinusParser.currentToken.getTokenType()) {
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                Params params = Params.parseParams();
                CMinusParser.matchToken(TokenType.RPAREN);
                Statement compoundStatement = Statement.parseCompoundStatement();
                return new FunctionDeclaration(params, compoundStatement);
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

    }

    public static Declaration parseDeclarationPrime() {
        Declaration decl = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case SEMI:
                CMinusParser.matchToken(Token.TokenType.SEMI);
                
                break;
            case LBRACKET:
                CMinusParser.matchToken(Token.TokenType.LBRACKET);
                CMinusParser.matchToken(Token.TokenType.NUM);
                CMinusParser.matchToken(Token.TokenType.RBRACKET);
                break;
            case INT:
                decl = parseFunctionDeclaration();
                break;
            case VOID:
                decl = parseFunctionDeclaration();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }
        return decl;

    }


}
