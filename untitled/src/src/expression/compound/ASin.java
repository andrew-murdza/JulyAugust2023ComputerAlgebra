package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ASin extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\arcsin\\left("+inside+"\\right)";
    }
}
