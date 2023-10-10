package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Abs extends Expression {
    public Expression inside;
    @Override
    public String toStringHelper() {
        return "\\left|"+inside+"\\right|";
    }
}
