package compiler.parser.declarations;

import java.io.FileWriter;
import java.io.IOException;

import compiler.parser.general.Params;
import compiler.parser.statements.Statement;

public class FunctionDeclaration extends Declaration {
    
    public Params params;
    public Statement compoundStatement;

    public FunctionDeclaration(Params params, Statement compoundStatement) {
        super();
        this.params = params;
        this.compoundStatement = compoundStatement;
    }

    public void print(FileWriter file) throws IOException {
        file.write(this.type + " ");
        identifierExpression.print(file);
        file.write(" (");
        params.print(file);
        file.write(")");
        compoundStatement.print(file);
    };
}
