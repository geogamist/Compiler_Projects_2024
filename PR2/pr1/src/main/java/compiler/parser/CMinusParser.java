package compiler.parser;

import compiler.parser.general.Program;
import compiler.scanner.Token;

public class CMinusParser implements Parser {

    static public Token currentToken;

    CMinusParser() {
        // Open file and get tokens
        currentToken = new Token();
    }

    public boolean matchToken() {
        return false;
    };

    public void advanceToken() {};

    public Program parse() {
        Program ast = new Program();
        return ast;
    };

    public void printTree() {

    };
}