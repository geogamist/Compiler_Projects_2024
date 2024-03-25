package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/** 
* This class extends Expression, implementing the CallExpression class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: CallExpression.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for call expressions. Users
* of this class are provided a method to print the class's contents.
*/

public class CallExpression extends Expression {

    Expression function;
    List<Expression> args;

    CallExpression(Expression function, List<Expression> args) {
        this.function = function;
        this.args = args;
    }

    public void print(FileWriter file, int spacer) throws IOException {
        function.print(file, spacer + 3);
        file.write("(");
        for (int i = 0; i < args.size(); i++) {
            args.get(i).print(file, -3);
            if ((i + 1) < args.size()) {
                
                file.write(", ");
            }
        }

        file.write(")");
    };
}
