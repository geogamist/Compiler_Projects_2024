package compiler.parser.declarations;
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

    void print() {};
}
