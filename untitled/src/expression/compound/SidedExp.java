package expression.compound;

import color.Color;
import expression.Expression;

public class SidedExp extends UniaryExpression {
    public Side side;//whether the limit from the right, left, an undefined side, or both sides
    public Color sideColor=Color.INHERIT;

    public SidedExp(Expression e,Side side) {
        super(e);
        this.side=side;
    }
    public SidedExp(Expression e, Side side, Color color) {
        this(e,side);
        this.color=color;
    }

    public enum Side{
        PLUS,MINUS,NAN,NONE;
    }

    @Override
    public String toStringHelper() {
        return e+(side==Side.NONE?"":"^{"+applyColor(sideColor,side==Side.PLUS?"+":side==Side.MINUS?"-":"\\pm"+"}"));
    }
}
