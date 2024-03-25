package compiler.parser.declarations;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.expressions.*;

/** 
* This class extends Declaration, implementing the VariableDeclaration class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: VariableDeclaration.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for variable declarations. Users
* of this class are provided a method to print the class's contents.
*/

public class VariableDeclaration extends Declaration {
    
    public VariableDeclaration(Expression identifierExpression) {
        super();
        this.identifierExpression = identifierExpression;
    }

    public void print(FileWriter file, int spacer) throws IOException {
        //file.write(type + " ");
        identifierExpression.print(file, spacer + 3);
    };
}
