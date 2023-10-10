package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Sin extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\sin\\left("+inside+"\\right)";
    }
}
