package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;

public class IdentifierExpression extends Expression {

    private String identifier;
    private Expression capacity;
    private boolean isArray;

    public IdentifierExpression(String identifier) {
        this.identifier = identifier;
        this.isArray = false;
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

    public void print(FileWriter file) throws IOException {
        file.write(identifier);
        if (isArray) {
            file.write("[");
            if (capacity != null) {
                capacity.print(file);
            }
            file.write("]");
        }
    };
}
