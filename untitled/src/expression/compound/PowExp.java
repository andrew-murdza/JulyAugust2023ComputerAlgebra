package expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PowExp extends Expression{
    public Expression base;
    public Expression pow;
}
