package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;

/** 
* This class extends Expression, implementing the AssignExpression class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: AssignExpression.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for assign expressions. Users
* of this class are provided a method to print the class's contents.
*/

public class AssignExpression extends Expression {

    Expression identifierExpression;
    Expression assignExpression;

    AssignExpression(Expression identifierExpression, Expression assignExpression) {
        this.identifierExpression = identifierExpression;
        this.assignExpression = assignExpression;
    }

    public void print(FileWriter file, int spacer) throws IOException {
        identifierExpression.print(file, spacer + 3);
        file.write(" = ");
        assignExpression.print(file, -3);
    };
}
