package expression.compound;

import expression.Expression;

public class ATan extends UniaryExpression {
    public ATan(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\arctan\\left("+e+"\\right)";
    }
}