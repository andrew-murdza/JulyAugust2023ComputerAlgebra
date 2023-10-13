package expression.compound;

import expression.Expression;
public class Sec extends UniaryExpression {

    public Sec(Expression e) {
        super(e);
    }

    @Override
    public String toStringHelper() {
        return "\\sec\\left("+e+"\\right)";
    }
}