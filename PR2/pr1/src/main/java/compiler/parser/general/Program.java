package compiler.parser.general;
import compiler.parser.CMinusParser;
import compiler.scanner.Token.*;

public class Program {
    Program parseProgram() {
        Program program = new Program();
        
        TokenType nextToken = CMinusParser.currentToken.getTokenType();
        if (nextToken == TokenType.VOID || nextToken == TokenType.INT) {
            
        }
        return program;
    }
    void print() {};
}
