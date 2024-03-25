package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CallExpression extends Expression {

    Expression function;
    List<Expression> args;

    CallExpression(Expression function, List<Expression> args) {
        this.function = function;
        this.args = args;
    }

    public void print(FileWriter file) throws IOException {
        function.print(file);
        file.write("(");
        for (int i = 0; i < args.size(); i++) {
            args.get(i).print(file);
            if ((i + 1) < args.size()) {
                file.write(", ");
            }
        }
        file.write(")");
    };
}
