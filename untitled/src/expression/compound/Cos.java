package expression.compound;

import expression.Expression;

public class Cos extends UniaryExpression {
    public Cos(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\cos\\left("+e+"\\right)";
    }
}