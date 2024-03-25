package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;

public class IdentifierExpression extends Expression {

    public String identifier;
    public Expression capacity;
    public boolean isArray;
    public String type;

    public IdentifierExpression(String identifier, String type) {
        this.type = type;
        this.identifier = identifier;
        this.isArray = false;
        this.capacity = new NumericExpression("0");
    }

    public IdentifierExpression(String identifier, Expression capacity, String type) {
        this.type = type;
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
        if (type != null) {
            file.write(type + " ");
        }
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
