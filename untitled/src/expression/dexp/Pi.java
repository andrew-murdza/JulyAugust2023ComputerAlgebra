package expression.dexp;

import color.Color;

public class Pi extends DoubleExp {
    public Pi(){
        super(Math.PI);
    }

    public Pi(Color color){
        super(Math.PI,color);
    }
    public Pi(Color color, int group){
        super(Math.PI,color,group);
    }

    @Override
    public String toStringHelper() {
        return "\\pi";
    }
}
