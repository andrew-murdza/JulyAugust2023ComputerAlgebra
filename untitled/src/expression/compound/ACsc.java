package expression.compound;

import expression.Expression;

public class ACsc extends UniaryExpression {
    public ACsc(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\arccsc\\left("+e+"\\right)";
    }
}
