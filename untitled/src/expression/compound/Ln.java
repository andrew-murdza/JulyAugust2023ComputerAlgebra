package expression.compound;

import expression.Expression;
import expression.dexp.E;

public class Ln extends Log {
    public Ln(Expression inside){
        super(inside,new E());
    }

    @Override
    public String toStringHelper() {
        return "\\ln\\left("+e+"\\right)";
    }
}
