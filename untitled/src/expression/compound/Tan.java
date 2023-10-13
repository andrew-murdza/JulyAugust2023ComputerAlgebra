package expression.compound;

import expression.Expression;

public class Tan extends UniaryExpression {

    public Tan(Expression e) {
        super(e);
    }

    @Override
    public String toStringHelper() {
        return "\\tan\\left("+e+"\\right)";
    }
}
