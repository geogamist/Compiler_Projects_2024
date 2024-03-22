package compiler.parser.general;
import compiler.parser.CMinusParser;
import compiler.parser.declarations.Declaration;
import compiler.scanner.Token.*;
import java.util.*;

public class Program {

    private List<Declaration> declarations;

    public Program() {
        declarations = new ArrayList<Declaration>();
    };

    public static Program parseProgram() {
        Program program = new Program();
        Declaration declaration = Declaration.parseDeclaration();
        program.declarations.add(declaration);
        
        while (CMinusParser.currentToken.getTokenType() == TokenType.VOID || 
               CMinusParser.currentToken.getTokenType() == TokenType.INT) {
            CMinusParser.advanceToken();
            Declaration newDeclaration = Declaration.parseDeclaration();
            program.declarations.add(newDeclaration);
        }

        return program;
    }

    public static void print() {};
}