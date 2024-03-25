package compiler.parser.general;
import compiler.parser.CMinusParser;
import compiler.parser.declarations.Declaration;
import compiler.scanner.Token.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Program {

    List<Declaration> declarations;

    public Program() {
        declarations = null;
    };

    public Program(List<Declaration> declarations) {
        this.declarations = declarations;
    };

    public static Program parseProgram() {

        Program program = null;
        Declaration declaration = null;
        List<Declaration> declarations = new ArrayList<>();

        while (CMinusParser.currentToken.getTokenType() == TokenType.VOID ||
               CMinusParser.currentToken.getTokenType() == TokenType.INT) {
            switch (CMinusParser.currentToken.getTokenType()) {
                case VOID:
                case INT:
                    declaration = Declaration.parseDeclaration();
                    declarations.add(declaration);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + CMinusParser.currentToken.getTokenType());
            }
        }

        program = new Program(declarations);
        return program;
    }

    public void print(FileWriter file) throws IOException {
        for (int i = 0; i < declarations.size(); i++) {
            declarations.get(i).print(file);
            file.write("\n");
        }
    };
}