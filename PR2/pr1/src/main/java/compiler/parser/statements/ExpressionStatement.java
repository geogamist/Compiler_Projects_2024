package compiler.parser.statements;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.expressions.Expression;

public class ExpressionStatement extends Statement {

    Expression expression;

    ExpressionStatement() {
        expression = null;
    }

    ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
    
    public void print(FileWriter file) throws IOException {
        if (expression != null) {
            expression.print(file);
        }
    };
}
