package expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParenExp extends Expression {
    public Expression e;

    @Override
    public String toStringHelper() {
        return "\\left("+e+"\\right)";
    }
}
