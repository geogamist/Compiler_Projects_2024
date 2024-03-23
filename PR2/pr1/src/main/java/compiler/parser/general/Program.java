package compiler.parser.general;
import compiler.parser.CMinusParser;
import compiler.parser.declarations.Declaration;
import compiler.scanner.Token.*;
import java.util.*;

import javax.lang.model.type.DeclaredType;

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
        Program program = new Program();
        Declaration lhs = Declaration.parseDeclaration();
        Declaration rhs = null;
        
        while (CMinusParser.currentToken.getTokenType() == TokenType.VOID || CMinusParser.currentToken.getTokenType() == TokenType.INT) {
            
            rhs = Declaration.parseDeclaration();
            program = new Program(lhs, rhs);

        }

        return program;
    }

    public static void print() {};
}