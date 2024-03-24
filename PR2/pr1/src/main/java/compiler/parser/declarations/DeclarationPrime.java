package compiler.parser.declarations;

import compiler.parser.expressions.*;


public class DeclarationPrime extends Declaration {

    public Declaration functionDeclaration;
    public Expression numericExpression;
    
    public DeclarationPrime(Declaration functionDeclaration) {
        super();
        this.functionDeclaration = functionDeclaration;
        this.numericExpression = null;
    }

    public DeclarationPrime(Expression numExpression) {
        super();
        this.functionDeclaration = null;
        this.numericExpression = numericExpression;
    }

    void print() {};
}
