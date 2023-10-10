package expression.compound;

import expression.Expression;
import expression.dexp.Int;

public class Sqrt extends NthRoot{
    public Sqrt(Expression inside){
        super(Int.of(2),inside);
    }
    public Expression sqrtRaw(Expression inside){
        return new NthRoot(Int.of(2),inside);
    }

    @Override
    public String toStringHelper() {
        return "\\sqrt{"+inside+"}";
    }
}
