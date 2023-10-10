package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Sec extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\sec\\left("+inside+"\\right)";
    }
}