package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cot extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\cot\\left("+inside+"\\right)";
    }
}
