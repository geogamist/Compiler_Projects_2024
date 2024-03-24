package compiler.parser.general;
import compiler.parser.CMinusParser;
import compiler.parser.declarations.Declaration;
import compiler.scanner.Token.*;

public class Program {

    Declaration lhs;
    Declaration rhs;

    public Program() {
        lhs = null;
        rhs = null;
    };

    public Program(Declaration lhs, Declaration rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    };

    public static Program parseProgram() {

        Program program = null;
        Declaration lhs = null;
        Declaration rhs = null;

        lhs = Declaration.parseDeclaration();
        program = new Program(lhs, rhs);
        while (CMinusParser.currentToken.getTokenType() == TokenType.VOID ||
               CMinusParser.currentToken.getTokenType() == TokenType.INT) {
            
            rhs = Declaration.parseDeclaration();
            program = new Program(lhs, rhs);
        }

        return program;
    }

    public static void print() {};
}