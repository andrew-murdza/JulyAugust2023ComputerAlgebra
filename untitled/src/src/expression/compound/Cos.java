package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cos extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\cos\\left("+inside+"\\right)";
    }
}