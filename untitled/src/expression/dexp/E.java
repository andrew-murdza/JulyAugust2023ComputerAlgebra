package expression.dexp;

import color.Color;

public class E extends DoubleExp{
    public E(){
        super(Math.E);
    }
    public E(Color color){
        super(Math.E,color);
    }
    public E(Color color, int group){
        super(Math.E,color,group);
    }

    @Override
    public String toStringHelper() {
        return "e";
    }
}
