package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ACsc extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\arccsc\\left("+inside+"\\right)";
    }
}
