package compiler.parser.expressions;

public class NumericExpression extends Expression {

    private int value;

    public NumericExpression(String value) {
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }

    void print() {};
}
