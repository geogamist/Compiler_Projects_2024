package compiler.parser.statements;

import compiler.parser.expressions.Expression;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CompoundStatement extends Statement {

    List<Expression> declarations;
    List<Statement> statement;

    CompoundStatement(List<Expression> declarations, List<Statement> statement) {
        this.declarations = declarations;
        this.statement = statement;
    }
    
    public void print(FileWriter file) throws IOException {
        file.write("\n{\n");
        for (int i = 0; i < declarations.size(); i++) {
            declarations.get(i).print(file);
            file.write("\n");
        }
        for (int i = 0; i < statement.size(); i++) {
            statement.get(i).print(file);
            file.write("\n");
        }
        file.write("}");
    };
}
