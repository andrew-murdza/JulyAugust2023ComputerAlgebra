package expression.compound;

import expression.Expression;

public class ASec extends UniaryExpression {
    public ASec(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\arcsec\\left("+e+"\\right)";
    }
}