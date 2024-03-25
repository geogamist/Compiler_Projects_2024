package compiler.parser.statements;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.expressions.Expression;

public class IterationStatement extends Statement {

    Expression expression;
    Statement statement;

    public IterationStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public void print(FileWriter file) throws IOException {
        file.write("while (");
        expression.print(file);
        file.write(") ");
        statement.print(file);
    };
}
