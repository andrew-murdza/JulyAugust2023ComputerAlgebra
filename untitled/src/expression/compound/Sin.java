package expression.compound;

import expression.Expression;

public class Sin extends UniaryExpression{

    public Sin(Expression e) {
        super(e);
    }

    @Override
    public String toStringHelper() {
        return "\\sin\\left("+e+"\\right)";
    }
}
