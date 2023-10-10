package src.expression.compound;

import expression.Expression;
import expression.Var;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class Lim extends Expression {
    public Var x=new Var("x");
    public SidedExp a;
    public Expression inside;

    @Override
    public String toStringHelper() {
        return "\\lim_{"+cleanUpScript(x+"\\to"+a)+"}"+inside;
    }
}
