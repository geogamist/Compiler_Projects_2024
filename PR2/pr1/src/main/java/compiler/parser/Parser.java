package compiler.parser;

public interface Parser {
    public BinaryExpression parse ();
    public void printTree ();
}