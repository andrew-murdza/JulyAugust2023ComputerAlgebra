package expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ACot extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\arccot\\left("+inside+"\\right)";
    }
}