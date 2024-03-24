package compiler.parser.statements;

import compiler.parser.expressions.Expression;
import java.util.*;

public class CompoundStatement extends Statement {

    List<Expression> declarations;
    List<Statement> statement;

    CompoundStatement(List<Expression> declarations, List<Statement> statement) {
        this.declarations = declarations;
        this.statement = statement;
    }
    
    void print() {};
}
