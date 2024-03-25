package compiler.parser.statements;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.expressions.Expression;

/** 
* This class extends Statement, implementing the ReturnStatement class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: ReturnStatement.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for return statements. Users
* of this class are provided a method to print the class's contents.
*/

public class ReturnStatement extends Statement {

    Expression expression;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }
    //print return statement
    public void print(FileWriter file, int spacer) throws IOException {
        //indentation
        for (int i = 0; i < spacer; i++) {
            file.write(" ");
        }
        file.write("return");
        //print ret expression if it exists
        if (expression != null) {
            file.write(" ");
            expression.print(file, -3 );
        }
    };
}
