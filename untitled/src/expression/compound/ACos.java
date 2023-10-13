package expression.compound;

import expression.Expression;

public class ACos extends UniaryExpression {
    public ACos(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\arccos\\left("+e+"\\right)";
    }
}
