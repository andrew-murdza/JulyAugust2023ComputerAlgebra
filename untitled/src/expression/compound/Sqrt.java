package expression.compound;

import expression.Expression;
import expression.dexp.Int;

public class Sqrt extends NthRoot{
    public Sqrt(Expression inside){
        super(inside,Int.of(2));
    }
    public Expression sqrtRaw(Expression inside){
        return new NthRoot(inside,Int.of(2));
    }

    @Override
    public String toStringHelper() {
        return "\\sqrt{"+e+"}";
    }
}
