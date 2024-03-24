package compiler.parser.expressions;

public class NumericExpression extends Expression {

    int value;

    public NumericExpression(String value) {
        super();
        this.value = Integer.parseInt(value);
    }

    void print() {};
}
