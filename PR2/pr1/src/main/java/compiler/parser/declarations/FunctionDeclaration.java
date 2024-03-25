package compiler.parser.declarations;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.general.Params;
import compiler.parser.statements.Statement;

/** 
* This class extends Declaration, implementing the FunctionDeclaration class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: FunctionDeclaration.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for function declarations. Users
* of this class are provided a method to print the class's contents.
*/

public class FunctionDeclaration extends Declaration {
    
    public Params params;
    public Statement compoundStatement;

    public FunctionDeclaration(Params params, Statement compoundStatement) {
        super();
        this.params = params;
        this.compoundStatement = compoundStatement;
    }

    public void print(FileWriter file, int spacer) throws IOException {

        identifierExpression.print(file, spacer + 3);
        for (int i = 0; i < spacer; i++) {
            file.write(" ");
        }
        file.write(" (");
        params.print(file, 0);

        file.write(")\n");
        compoundStatement.print(file, spacer + 3);
    };
}
