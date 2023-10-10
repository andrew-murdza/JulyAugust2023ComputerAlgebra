package expression.compound;

import expression.Expression;
import expression.dexp.E;

public class Log extends Expression {
    public Expression base;
    public Expression inside;

    protected Log(Expression base, Expression inside){
        this.base=base;
        this.inside=inside;
    }

    @Override
    public String toStringHelper() {
        return "\\log_{"+Expression.cleanUpScript(base.toString())+"}\\left("+inside+"\\right)";
    }
    public Log ofRaw(Expression base, Expression inside){
        return new Log(base,inside);
    }
    public Log of(Expression base, Expression inside){
        return base.equals(new E())?new Ln(inside):new Log(base,inside);
    }
}
