package expression.compound;

import expression.Expression;
import expression.dexp.Int;

public class NthRoot extends Expression{
    public Expression n;
    public Expression inside;
    NthRoot(Expression n, Expression inside){
        this.n=n;
        this.inside=inside;
    }
    private NthRoot of(Expression n, Expression inside){
        return n.equals(Int.of(2))?new Sqrt(inside):new NthRoot(n,inside);
    }

    @Override
    public String toStringHelper() {
        return "\\sqrt["+Expression.cleanUpScript(n.toString())+"]{"+inside+"}";
    }
}
