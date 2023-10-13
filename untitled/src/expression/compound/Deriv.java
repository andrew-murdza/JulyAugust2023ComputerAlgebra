package expression.compound;

import expression.Expression;
import expression.Var;


public class Deriv extends UniaryExpression {
    public Var x=new Var("x");

    public Deriv(Expression e) {
        super(e);
    }
    public Deriv(Expression e, Var x){
        this(e);
        this.x=x;
    }

    @Override
    public String toStringHelper() {
        return "\\frac{d}{d"+x+"}\\left("+e+"}";
    }
}
