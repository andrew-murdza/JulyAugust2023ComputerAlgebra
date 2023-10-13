package expression.compound;

import expression.Expression;

public class Frac extends BinaryExpression {

    public Frac(Expression e, Expression f) {
        super(e, f);
    }

    @Override
    public String toStringHelper() {
        return "\\frac{"+e+"}{"+f+"}";
    }
}
