package expression.dexp;

import color.Color;
import expression.Expression;

public class DoubleExp extends Expression {
    public double val;
    public Color color;
    protected DoubleExp(double val){
        this(val,Color.INHERIT);
    }
    protected DoubleExp(double val, Color color){
        super(color);
        this.val=val;
    }
    protected DoubleExp(double val, Color color,int group){
        super(color,group);
        this.val=val;
    }

    public static DoubleExp of(double val,Color color){
        return val%1==0?Int.of((int)val,color):new DoubleExp(val,color);
    }
    public static DoubleExp of(double val){
        return of(val,Color.INHERIT);
    }

    @Override
    public String toStringHelper() {
        return val+"";
    }
}
