package compiler.parser.statements;

import compiler.parser.expressions.Expression;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/** 
* This class extends Statement, implementing the CompoundStatement class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: CompoundStatement.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for compound statements. Users
* of this class are provided a method to print the class's contents.
*/

public class CompoundStatement extends Statement {

    List<Expression> declarations;
    List<Statement> statement;
    //constructor
    CompoundStatement(List<Expression> declarations, List<Statement> statement) {
        this.declarations = declarations;
        this.statement = statement;
    }
    
    public void print(FileWriter file, int spacer) throws IOException {
        //indentation
        for (int i = 0; i < spacer; i++) {
            file.write(" ");
        }
        file.write("{\n");
        //print declarations
        for (int i = 0; i < declarations.size(); i++) {
            declarations.get(i).print(file, spacer + 3);
            file.write("\n");
        }
        //print statements
        for (int i = 0; i < statement.size(); i++) {
            statement.get(i).print(file, spacer + 3);
            file.write("\n");
        }
        for (int i = 0; i < spacer; i++) {
            file.write(" ");
        }
        file.write("}");
    };
}
