package src.expression.compound;

import expression.Expression;
import expression.dexp.E;

public class Ln extends Log {
    public Ln(Expression inside){
        super(new E(),inside);
    }

    @Override
    public String toStringHelper() {
        return "\\ln\\left("+inside+"\\right)";
    }
}
