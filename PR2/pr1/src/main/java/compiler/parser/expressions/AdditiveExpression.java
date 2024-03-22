package compiler.parser.expressions;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;

public class AdditiveExpression extends Expression {

    AdditiveExpression() {
        super();
    };

    public static Expression parseAdditiveExpression() {

        Expression lhs = parseTerm();

        while (isAddop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseTerm();
            lhs = createBinopExpression(oldToken, lhs, rhs);
        }

        return lhs;
    }

    public void print() {};
}
