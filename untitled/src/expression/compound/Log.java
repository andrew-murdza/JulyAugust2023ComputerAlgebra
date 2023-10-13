package expression.compound;

import expression.Expression;
import expression.dexp.E;

public class Log extends BinaryExpression {

    protected Log(Expression e, Expression f) {
        super(e, f);
    }

    public static Log logRaw(Expression inside, Expression base){
        return new Log(inside,base);
    }

    @Override
    public String toStringHelper() {
        return "\\log_{"+Expression.cleanUpScript(f.toString())+"}\\left("+e+"\\right)";
    }
    public Log ofRaw(Expression inside, Expression base){
        return new Log(inside,base);
    }
    public static Log of(Expression base, Expression inside){
        return base.equals(new E())?new Ln(inside):new Log(inside,base);
    }
}
