package compiler.parser.statements;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.expressions.Expression;

/** 
* This class extends Statement, implementing the SelectionStatement class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: SelectionStatement.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for selection statements. Users
* of this class are provided a method to print the class's contents.
*/

public class SelectionStatement extends Statement {

    Expression expression;
    Statement thenStatement;
    Statement elseStatement;

    public SelectionStatement(Expression expression, Statement thenStatement, Statement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }
    
    public void print(FileWriter file, int spacer) throws IOException {
        //indentation
        for (int i = 0; i < spacer; i++) {
            file.write(" ");
        }
        file.write("if (");
        //print expressions inside if condition
        expression.print(file, -3);

        file.write(")\n");
        //print statements inside if
        thenStatement.print(file, spacer);

        //print else statement if it exists
        if (elseStatement != null) {
            file.write("\n");

            for (int i = 0; i < spacer; i++) {
                file.write(" ");
            }
            file.write("else ");
            //print else statement
            elseStatement.print(file, 0);
        }
    };
}
