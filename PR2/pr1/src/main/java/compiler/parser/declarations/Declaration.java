package compiler.parser.declarations;
import compiler.parser.CMinusParser;
import compiler.parser.expressions.NumericExpression;
import compiler.scanner.Token;
import compiler.scanner.Token.TokenType;
import compiler.parser.general.Param;
import compiler.parser.statements.Statement;

public abstract class Declaration {

    public String identifier;
    public String type;

    public Declaration() {
        identifier = null;
        type = null;
    }

    public static Declaration parseDeclaration() {

        Declaration declaration = null;
        String identifier = null;
        String type = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case VOID:
                type = (String)CMinusParser.matchToken(TokenType.VOID);
                identifier = (String)CMinusParser.matchToken(TokenType.ID);
                declaration = parseFunctionDeclaration();
                declaration.intialize(type, identifier);
                break;
            case INT:
                type = (String)CMinusParser.matchToken(TokenType.INT);
                identifier = (String)CMinusParser.matchToken(TokenType.ID);
                declaration = parseDeclarationPrime();
                declaration.intialize(type, identifier);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return declaration;
    };

    public static Declaration parseFunctionDeclaration() {

        Declaration declaration = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case LPAREN:
                CMinusParser.matchToken(TokenType.LPAREN);
                Param params = Param.parseParams();
                CMinusParser.matchToken(TokenType.RPAREN);
                Statement compoundStatement = Statement.parseCompoundStatement();
                declaration = new FunctionDeclaration(params, compoundStatement);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return declaration;
    }

    public static Declaration parseDeclarationPrime() {

        Declaration declaration = null;
        String number = null;

        switch (CMinusParser.currentToken.getTokenType()) {
            case SEMI:
                CMinusParser.matchToken(Token.TokenType.SEMI);
                break;
            case LBRACKET:
                CMinusParser.matchToken(Token.TokenType.LBRACKET);
                number = (String)CMinusParser.matchToken(Token.TokenType.NUM);
                NumericExpression numericExpression = new NumericExpression(number);
                declaration = new DeclarationPrime(numericExpression);
                CMinusParser.matchToken(Token.TokenType.RBRACKET);
                break;
            case LPAREN:
                declaration = parseFunctionDeclaration();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
        }

        return declaration;
    }

    private void intialize(String type, String identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    abstract void print();
}
