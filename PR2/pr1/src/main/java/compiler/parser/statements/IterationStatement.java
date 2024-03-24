package compiler.parser.statements;

import compiler.parser.expressions.Expression;

public class IterationStatement extends Statement {

    Expression expression;
    Statement statement;

    public IterationStatement(Expression expression, Statement statement) {
        super();
        this.expression = expression;
        this.statement = statement;
    }

    void print() {};
}
