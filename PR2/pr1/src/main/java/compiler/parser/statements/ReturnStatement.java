package compiler.parser.statements;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.expressions.Expression;

public class ReturnStatement extends Statement {

    Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    public void print(FileWriter file) throws IOException {
        file.write("return");
        if (expression != null) {
            file.write(" ");
            expression.print(file);
        }
    };
}
