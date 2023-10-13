package expression.compound;

import expression.Expression;
public class ACot extends UniaryExpression {
    public ACot(Expression e) {
        super(e);
    }
    @Override
    public String toStringHelper() {
        return "\\arccot\\left("+e+"\\right)";
    }
}