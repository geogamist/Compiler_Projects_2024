package compiler.parser.expressions;

public class IdentifierExpression extends Expression {

    String identifier;
    Expression capacity;

    public IdentifierExpression(String identifier, Expression capacity) {
        super();
        this.identifier = identifier;
        this.capacity = capacity;
    }

    void print() {};
}
