package expression.compound;

import expression.Expression;
import expression.Var;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class Deriv extends Expression {
    public Var x=new Var("x");
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\frac{d}{d"+x+"}\\left("+inside+"}";
    }
}
