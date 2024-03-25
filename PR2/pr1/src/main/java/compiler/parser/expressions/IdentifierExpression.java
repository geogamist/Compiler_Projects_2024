package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;

/** 
* This class extends Expression, implementing the IdentifierExpression class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: IdentifierExpression.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for identifier expressions. Users
* of this class are provided methods to print the class's contents and get
* the class's member variables.
*/

public class IdentifierExpression extends Expression {

    public String identifier;
    public Expression capacity;
    public boolean isArray;
    public String type;

    public IdentifierExpression(String identifier, String type) {
        this.type = type;
        this.identifier = identifier;
        this.isArray = false;
        this.capacity = new NumericExpression("0");
    }

    public IdentifierExpression(String identifier, Expression capacity, String type) {
        this.type = type;
        this.identifier = identifier;
        this.isArray = true;
        this.capacity = capacity;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getCapacity() {
        return capacity;
    }

    public void print(FileWriter file, int spacer) throws IOException { 
        for (int i = 0; i < spacer; i++) {
            file.write(" ");
        }
        if (type != null) {

            file.write(type + " ");
        }
        // for (int i = 0; i < spacer; i++) {
        //     file.write(" ");
        // }
        file.write(identifier);
        if (isArray) {

            file.write("[");
            if (capacity != null) {
                capacity.print(file, 0);
            }
            file.write("]");
        }
    };
}
