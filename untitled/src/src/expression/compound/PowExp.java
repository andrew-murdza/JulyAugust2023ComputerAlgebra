package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PowExp extends Expression{
    public Expression base;
    public Expression pow;

    @Override
    public String toStringHelper() {
        return base+"^{"+Expression.cleanUpScript(pow.toString())+"}";
    }
}