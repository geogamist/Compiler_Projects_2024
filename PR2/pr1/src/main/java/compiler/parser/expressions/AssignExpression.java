package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;

public class AssignExpression extends Expression {

    Expression identifierExpression;
    Expression assignExpression;

    AssignExpression(Expression identifierExpression, Expression assignExpression) {
        this.identifierExpression = identifierExpression;
        this.assignExpression = assignExpression;
    }

    public void print(FileWriter file) throws IOException {
        identifierExpression.print(file);
        file.write(" = ");
        assignExpression.print(file);
    };
}
