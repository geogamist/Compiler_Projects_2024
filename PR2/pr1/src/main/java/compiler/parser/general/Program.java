package compiler.parser.general;
import compiler.parser.CMinusParser;
import compiler.parser.declarations.Declaration;
import compiler.scanner.Token.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/** 
* This class implements the Program class.
*
* @author Abagail Clark, Josiah Harvey, Spencer Riffle
* @version 1.0
* File: Program.java
* Created: March 2024
* ©Copyright Cedarville University, its Computer Science faculty, and the 
* authors. All rights reserved.
*
* Description: This class provides a class for programs. Users of this
* class are provided methods to parse the program, including all of its
* declarations.
*/

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
                    throw new IllegalArgumentException("Unexpected value parseProgram: " + CMinusParser.currentToken.getTokenType());
            }
        }

        program = new Program(declarations);
        return program;
    }

    public void print(FileWriter file) throws IOException {
        file.write("Program\n{\n");
        for (int i = 0; i < declarations.size(); i++) {
            declarations.get(i).print(file, 0);
            file.write("\n");
        }
        file.write("}\n");
    };
}