package compiler.parser.expressions;

public class AssignExpression extends Expression {

    Expression rhs;

    AssignExpression(Expression rhs) {
        super();
        this.rhs = rhs;
    }

    void print() {};
}
