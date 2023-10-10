package expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ASec extends Expression {
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\arcsec\\left("+inside+"\\right)";
    }
}
