package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Frac extends Expression {
    public Expression num;
    public Expression denom;

    @Override
    public String toStringHelper() {
        return "\\frac{"+num+"}{"+denom+"}";
    }
}
