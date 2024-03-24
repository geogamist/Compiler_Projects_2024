package compiler.parser.declarations;
import compiler.parser.general.Param;
import compiler.parser.statements.Statement;

public class FunctionDeclaration extends Declaration {
    public Param params;
    public Statement compoundStatement;

    public FunctionDeclaration(Param params, Statement compoundStatement) {
        super();
        this.params = params;
        this.compoundStatement = compoundStatement;
    }

    void print() {};
}
