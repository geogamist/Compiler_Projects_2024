package compiler.parser.statements;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.expressions.Expression;

/** 
* This class extends Statement, implementing the ExpressionStatement class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: ExpressionStatement.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for expression statements. Users
* of this class are provided a method to print the class's contents.
*/

public class ExpressionStatement extends Statement {

    Expression expression;

    ExpressionStatement() {
        expression = null;
    }

    ExpressionStatement(Expression expression) {
        this.expression = expression;
    }
    
    public void print(FileWriter file, int spacer) throws IOException {
        if (expression != null) {
            expression.print(file, spacer -3 );
        }
    };
}
