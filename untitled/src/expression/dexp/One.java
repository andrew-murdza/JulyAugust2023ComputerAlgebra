package expression.dexp;

import color.Color;

public class One extends Int {
    public One(){
        super(1);
    }
    public One(Color color){
        super(1,color);
    }
    public One(Color color, int group){
        super(1,color,group);
    }
}
