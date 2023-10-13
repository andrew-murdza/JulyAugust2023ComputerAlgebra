package expression.compound;

import expression.Expression;

public class Floor extends UniaryExpression {
    public Floor(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\left\\lfloor"+e+"\\right\\rfloor";
    }
}
