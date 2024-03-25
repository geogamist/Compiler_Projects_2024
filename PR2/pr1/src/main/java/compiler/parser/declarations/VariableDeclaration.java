package compiler.parser.declarations;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.expressions.*;


public class VariableDeclaration extends Declaration {
    
    public VariableDeclaration(Expression identifierExpression) {
        super();
        this.identifierExpression = identifierExpression;
    }

    public void print(FileWriter file) throws IOException {
        //file.write(type + " ");
        identifierExpression.print(file);
    };
}
