package expression.compound;

import expression.Expression;
import expression.dexp.Int;

public class Sqrt extends NthRoot{
    public Sqrt(Expression inside){
        super(Int.of(2),inside);
    }
}
