package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;

/** 
* This class extends Expression, implementing the NumericExpression class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: NumericExpression.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for numeric expressions. Users
* of this class are provided methods to print the class's contents and get
* the class's member variable.
*/

public class NumericExpression extends Expression {

    private int value;

    public NumericExpression(String value) {
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }

    public void print(FileWriter file, int spacer) throws IOException {
        // for (int i = 0; i < spacer; i++) {
        //     file.write(" ");
        // }
        file.write(Integer.toString(value));
    };
}
