package expression.compound;

import expression.Expression;
import expression.Var;


public class Lim extends UniaryExpression {
    public Var x=new Var("x");
    public SidedExp a;

    public Lim(Expression e, SidedExp a) {
        super(e);
        this.a=a;
    }
    public Lim(Expression e, SidedExp a, Var x){
        this(e,a);
        this.x=x;
    }

    @Override
    public String toStringHelper() {
        return "\\lim_{"+cleanUpScript(x+"\\to"+a)+"}"+e;
    }
}
