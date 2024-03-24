package compiler.parser.statements;

import compiler.parser.expressions.Expression;

public class ExpressionStatement extends Statement {

    Expression expression;

    ExpressionStatement() {
        super();
        expression = null;
    }

    ExpressionStatement(Expression expression) {
        super();
        this.expression = expression;
    }
    
    void print() {};
}
