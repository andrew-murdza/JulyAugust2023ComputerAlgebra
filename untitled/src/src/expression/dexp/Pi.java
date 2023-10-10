package src.expression.dexp;

import expression.dexp.DoubleExp;

public class Pi extends DoubleExp {
    public Pi(){
        super(Math.PI);
    }

    @Override
    public String toStringHelper() {
        return "\\pi";
    }
}
