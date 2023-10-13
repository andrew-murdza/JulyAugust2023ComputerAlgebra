package expression.compound;

import expression.Expression;
import expression.dexp.Int;

public class NthRoot extends BinaryExpression{
    protected NthRoot(Expression inside,Expression n){
        super(inside,n);
    }
    public static NthRoot of(Expression inside, Expression n){
        return n.equals(Int.of(2))?new Sqrt(inside):new NthRoot(inside,n);
    }
    public static NthRoot rawOf(Expression inside, Expression n){
        return new NthRoot(inside,n);
    }

    @Override
    public String toStringHelper() {
        return "\\sqrt["+Expression.cleanUpScript(f.toString())+"]{"+e+"}";
    }
}
