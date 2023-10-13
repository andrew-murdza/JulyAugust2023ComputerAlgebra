package expression.compound;

import expression.Expression;
import expression.Var;

public class AntiDeriv extends UniaryExpression {
    public Var x=new Var("x");

    public AntiDeriv(Expression e) {
        super(e);
    }
    public AntiDeriv(Expression e, Var x){
        this(e);
        this.x=x;
    }

    @Override
    public String toStringHelper() {
        return "\\int"+e+"\\,\\text{d}"+x;
    }
}
