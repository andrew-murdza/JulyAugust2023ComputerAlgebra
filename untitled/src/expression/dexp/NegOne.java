package expression.dexp;

import color.Color;

public class NegOne extends Int {
    public NegOne(){
        super(-1);
    }
    public NegOne(Color color){
        super(-1,color);
    }

    public NegOne(Color color, int group){
        super(-1,color,group);
    }
}
