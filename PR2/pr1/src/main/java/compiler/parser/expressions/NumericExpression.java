package compiler.parser.expressions;

import compiler.scanner.Token;

public class NumericExpression extends Expression {

    int value;

    public NumericExpression(Token token) {
        super();
        this.value = (int)token.getTokenData();
    }

    void print() {};
}
