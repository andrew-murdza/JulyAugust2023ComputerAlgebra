package expression.compound;

import expression.Expression;

public class Floor extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\left\\lfloor"+inside.toString()+"\\right\\rfloor";
    }
}
