package expression.compound;

import expression.Expression;
import expression.Var;
import expression.dexp.DoubleExp;

public class PowExp extends BinaryExpression{

    public PowExp(Expression e, Expression f) {
        super(e, f);
    }

    @Override
    public String toStringHelper() {
        return (e instanceof DoubleExp||e instanceof Var?e:"("+e+")") +"^{"+Expression.cleanUpScript(f.toString())+"}";
    }
}
