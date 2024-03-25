package compiler.parser.expressions;

import java.io.FileWriter;
import java.io.IOException;

import compiler.scanner.Token;

public class BinaryExpression extends Expression {

    Expression lhs;
    Expression rhs;
    String operator;

    public BinaryExpression(Token token, Expression lhs, Expression rhs) {
        switch (token.getTokenType()) {
            case TIMES:
                operator = "*";
                break;
            case OVER:
                operator = "/";
                break;
            case PLUS:
                operator = "+";
                break;
            case MINUS:
                operator = "-";
                break;
            case GT:
                operator = ">";
                break;
            case LT:
                operator = "<";
                break;
            case GE:
                operator = ">=";
                break;
            case LE:
                operator = "<=";
                break;
            case NE:
                operator = "!=";
                break;
            case EQ:
                operator = "==";
                break;
            default:
                break;
        }
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void print(FileWriter file) throws IOException {
        lhs.print(file);
        file.write(operator);
        rhs.print(file);
    };
}
