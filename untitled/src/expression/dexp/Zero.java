package expression.dexp;

import color.Color;

public class Zero extends Int {
    public Zero(){
        super(0);
    }
    public Zero(Color color){
        super(0,color);
    }
    public Zero(Color color, int group){
        super(0,color,group);
    }
}
