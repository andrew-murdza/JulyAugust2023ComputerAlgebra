package expression.dexp;

import color.Color;

public class Int extends DoubleExp {
    public int i;
    protected Int(int i, Color color){
        super(i,color);
        this.i=i;
    }
    protected Int(int i){
        super(i);
        this.i=i;
    }
    public static Int of(int val,Color color){
        return val==0?new Zero(color):val==-1?new NegOne(color):val==1?new One(color):new Int(val,color);
    }
    public static Int of(int val){
        return of(val,Color.INHERIT);
    }
}
