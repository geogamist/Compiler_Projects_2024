package compiler.parser.statements;

import compiler.parser.expressions.Expression;

public class ExpressionStatement extends Statement {

    Expression expression;

    ExpressionStatement() {
        expression = null;
    }

    ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
    
    void print() {};
}
