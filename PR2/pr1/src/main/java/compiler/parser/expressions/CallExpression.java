package compiler.parser.expressions;

import java.util.*;

public class CallExpression extends Expression {

    Expression function;
    List<Expression> args;

    CallExpression(Expression function, List<Expression> args) {
        this.function = function;
        this.args = args;
    }

    void print() {};
}
