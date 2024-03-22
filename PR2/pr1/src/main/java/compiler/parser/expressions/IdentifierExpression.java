package compiler.parser.expressions;

import compiler.scanner.Token;

public class IdentifierExpression extends Expression {

    String identifier;

    public IdentifierExpression(Token token) {
        super();
        this.identifier = (String)token.getTokenData();
    }

    void print() {};
}
