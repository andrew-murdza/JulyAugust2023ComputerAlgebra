package expression.compound;

import expression.Expression;
import expression.Var;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class AntiDeriv extends Expression {
    public Var x=new Var("x");
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\int"+inside+"\\,\\text{d}"+x;
    }
}
