package compiler.parser.statements;

import compiler.parser.expressions.Expression;

public class SelectionStatement extends Statement {

    Expression expression;
    Statement thenStatement;
    Statement elseStatement;

    public SelectionStatement(Expression expression, Statement thenStatement, Statement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }
    
    void print() {};
}
