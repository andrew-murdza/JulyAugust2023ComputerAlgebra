package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ACos extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\arccos\\left("+inside+"\\right)";
    }
}
