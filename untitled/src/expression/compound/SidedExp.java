package expression.compound;

import color.Color;
import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SidedExp extends Expression {
    public Expression e;
    public Side side;//whether the limit from the right, left, an undefined side, or both sides
    public Color sideColor=Color.INHERIT;
    public enum Side{
        PLUS,MINUS,NAN,NONE;
    }

    @Override
    public String toStringHelper() {
        return e+applyColor(sideColor,side==Side.PLUS?"^{+}":side==Side.MINUS?"^{-}":side==Side.NONE?"":"^{\\pm}");
    }
}
