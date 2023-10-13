package expression.compound;

import expression.Expression;

public class Cot extends UniaryExpression {
    public Cot(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\cot\\left("+e+"\\right)";
    }
}
