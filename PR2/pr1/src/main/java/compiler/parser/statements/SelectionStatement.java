package compiler.parser.statements;

import java.io.FileWriter;
import java.io.IOException;

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
    
    public void print(FileWriter file) throws IOException {
        file.write("if (");
        expression.print(file);
        file.write(")\n");
        thenStatement.print(file);
        file.write("\n");
        if (elseStatement != null) {
            file.write("else ");
            elseStatement.print(file);
        }
    };
}
