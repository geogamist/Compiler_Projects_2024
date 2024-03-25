package compiler.parser.statements;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.expressions.Expression;

/** 
* This class extends Statement, implementing the IterationStatement class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: IterationStatement.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for iteration statements. Users
* of this class are provided a method to print the class's contents.
*/

public class IterationStatement extends Statement {

    Expression expression;
    Statement statement;

    public IterationStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    public void print(FileWriter file, int spacer) throws IOException {

        //indentation
        for (int i = 0; i < spacer; i++) {
            file.write(" ");
        }
        file.write("while (");
        //print expressions inside while loop condition
        expression.print(file, -3);
        
        // for (int i = 0; i < spacer; i++) {
        //     file.write(" ");
        // }
        file.write(")\n");
        //print statements inside while loop
        statement.print(file, spacer);
    };
}
