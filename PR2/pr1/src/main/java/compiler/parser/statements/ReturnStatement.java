package compiler.parser.statements;

import compiler.parser.expressions.Expression;

public class ReturnStatement extends Statement {

    Expression expression;

    public ReturnStatement(Expression expression) {
        super();
        this.expression = expression;
    }

    void print() {};
}
