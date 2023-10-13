package expression.compound;

import expression.Expression;

public class Abs extends UniaryExpression {

    public Abs(Expression e) {
        super(e);
    }

    @Override
    public String toStringHelper() {
        return "\\left|"+e+"\\right|";
    }
}
