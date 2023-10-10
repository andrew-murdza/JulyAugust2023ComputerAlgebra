package src.expression.dexp;

import expression.dexp.DoubleExp;

public class E extends DoubleExp {
    public E(){
        super(Math.E);
    }

    @Override
    public String toStringHelper() {
        return "e";
    }
}
