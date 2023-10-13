package expression.compound;

import expression.Expression;

public class Csc extends UniaryExpression {
    public Csc(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\csc\\left("+e+"\\right)";
    }
}
