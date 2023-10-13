package expression.compound;

import expression.Expression;

public class ParenExp extends UniaryExpression {

    public ParenExp(Expression e) {
        super(e);
    }

    @Override
    public String toStringHelper() {
        return "\\left("+e+"\\right)";
    }
}
