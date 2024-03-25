package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;

public class NumericExpression extends Expression {

    private int value;

    public NumericExpression(String value) {
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }

    public void print(FileWriter file) throws IOException {
        file.write(Integer.toString(value));
    };
}
