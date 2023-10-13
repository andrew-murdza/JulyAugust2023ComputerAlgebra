package expression.compound;

import expression.Expression;

public class ASin extends UniaryExpression {
    public ASin(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\arcsin\\left("+e+"\\right)";
    }
}
