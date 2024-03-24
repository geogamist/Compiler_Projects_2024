package compiler.parser.expressions;

public class AssignExpression extends Expression {

    Expression identifierExpression;
    Expression assignExpression;

    AssignExpression(Expression identifierExpression, Expression assignExpression) {
        this.identifierExpression = identifierExpression;
        this.assignExpression = assignExpression;
    }

    void print() {};
}
