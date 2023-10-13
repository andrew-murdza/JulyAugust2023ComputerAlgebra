package expression;

import color.Color;

public class Inf extends Expression {

    public Inf(Color color, int group){
        super(color,group);
    }
    public Inf(Color color){
        super(color);
    }
    public Inf(){
        super();
    }

    public static Expression of(boolean pos){
        return pos?new Inf():new Inf().negate();
    }
    public static Expression of(boolean pos, Color color){
        return pos?new Inf(color):new Inf(color).negate();
    }
    public static Expression of(boolean pos, Color color, int group){
        return pos?new Inf(color,group):new Inf(color,group).negate();
    }
}
