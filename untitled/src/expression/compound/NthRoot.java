package expression.compound;

import expression.Expression;
import expression.dexp.Int;
import lombok.AllArgsConstructor;

public class NthRoot extends Expression{
    public Expression n;
    public Expression inside;
    private NthRoot(Expression n, Expression inside){
        this.n=n;
        this.inside=inside;
    }
    private NthRoot of(Expression n, Expression inside){
        return n.equals(Int.of(2))?new Sqrt(inside):new NthRoot(n,inside);
    }
}
