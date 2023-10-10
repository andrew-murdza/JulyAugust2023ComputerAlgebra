package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;p@AllArgsConstructor
public class Csc extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\csc\\left("+inside+"\\right)";
    }
}
