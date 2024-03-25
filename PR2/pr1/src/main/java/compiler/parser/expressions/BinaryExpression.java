package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;

import compiler.scanner.Token;

/** 
* This class extends Expression, implementing the BinaryExpression class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: BinaryExpression.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for binary expressions. Users
* of this class are provided a method to print the class's contents.
*/

public class BinaryExpression extends Expression {

    Expression lhs;
    Expression rhs;
    String operator;

    public BinaryExpression(Token token, Expression lhs, Expression rhs) {
        switch (token.getTokenType()) {
            case TIMES:
                operator = "*";
                break;
            case OVER:
                operator = "/";
                break;
            case PLUS:
                operator = "+";
                break;
            case MINUS:
                operator = "-";
                break;
            case GT:
                operator = ">";
                break;
            case LT:
                operator = "<";
                break;
            case GE:
                operator = ">=";
                break;
            case LE:
                operator = "<=";
                break;
            case NE:
                operator = "!=";
                break;
            case EQ:
                operator = "==";
                break;
            default:
                break;
        }
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void print(FileWriter file, int spacer) throws IOException {
        lhs.print(file, spacer + 3);
        // for (int i = 0; i < spacer; i++) {
        //     file.write(" ");
        // }
        file.write(" " + operator + " ");
        rhs.print(file, -3);
        
    };
}
