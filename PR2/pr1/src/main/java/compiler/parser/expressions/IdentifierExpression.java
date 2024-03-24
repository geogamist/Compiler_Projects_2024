package compiler.parser.expressions;

public class IdentifierExpression extends Expression {

    private String identifier;
    private Expression capacity;
    private boolean isArray;

    public IdentifierExpression(String identifier) {
        this.identifier = identifier;
        this.isArray = true;
        this.capacity = new NumericExpression("0");
    }

    public IdentifierExpression(String identifier, Expression capacity) {
        this.identifier = identifier;
        this.isArray = true;
        this.capacity = capacity;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getCapacity() {
        return capacity;
    }

    public boolean isArray() {
        return isArray;
    }

    void print() {};
}
