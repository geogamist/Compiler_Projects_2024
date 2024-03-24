package compiler.parser.expressions;

public class CallExpression extends Expression {

    Expression identifierExpression;
    Expression varCall;

    CallExpression(Expression identifierExpression, Expression varCall) {
        super();
        this.identifierExpression = identifierExpression;
        this.varCall = varCall;
    }

    void print() {};
}
