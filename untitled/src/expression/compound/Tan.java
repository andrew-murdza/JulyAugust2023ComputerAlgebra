package expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Tan extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\tan\\left("+inside+"\\right)";
    }
}
