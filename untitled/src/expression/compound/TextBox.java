package expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TextBox extends Expression {
    public Expression e;
    @Override
    public String toStringHelper() {
        return "\\tbs{"+e+"}";
    }
}
