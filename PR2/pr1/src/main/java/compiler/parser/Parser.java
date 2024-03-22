package compiler.parser;

import compiler.parser.general.Program;

public interface Parser {
    public Program parse();
    public void printTree();
}