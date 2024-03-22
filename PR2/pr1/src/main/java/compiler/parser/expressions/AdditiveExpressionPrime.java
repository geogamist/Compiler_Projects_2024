package compiler.parser.expressions;

import compiler.parser.CMinusParser;
import compiler.scanner.Token;

public class AdditiveExpressionPrime extends Expression {
    
    AdditiveExpressionPrime() {
        super();
    };

    public static Expression parseAdditiveExpressionPrime() {

        Expression lhs = parseTermPrime();

        while (isAddop(CMinusParser.currentToken.getTokenType())) {
            Token oldToken = CMinusParser.advanceToken();
            Expression rhs = parseTerm();
            lhs = createBinopExpression(oldToken, lhs, rhs);
        }

        return lhs;
    }

    void print() {};
}
