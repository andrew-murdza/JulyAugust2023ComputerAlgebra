package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ATan extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\arctan\\left("+inside+"\\right)";
    }
}