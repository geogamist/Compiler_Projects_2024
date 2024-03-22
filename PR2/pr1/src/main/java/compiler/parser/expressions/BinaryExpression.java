package compiler.parser.expressions;

import compiler.scanner.Token;
import compiler.scanner.Token.TokenType;

public class BinaryExpression extends Expression {

    Expression lhs;
    Expression rhs;
    TokenType operator;

    public BinaryExpression(Token token, Expression lhs, Expression rhs) {
        super();
        this.operator = token.getTokenType();
        this.lhs = lhs;
        this.rhs = rhs;
    }

    void print() {};
}
