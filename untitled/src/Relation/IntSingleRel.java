package Relation;

import color.Color;
import expression.Expression;
import expression.Inf;
import expression.Var;
import expression.dexp.Int;
import set.Interval;
import set.IntervalUnion;
import set.Set;

public class IntSingleRel {
    public Side side;
    public Rel.Sign sign;
    public Color color;
    public Expression a;
    public Var x;
    public Rel toRel(){
        Expression left=x;
        Expression right=a;
        Rel.Sign sign1=sign;
        if(side==Side.RIGHT){
            left=a;
            right=x;
            sign1= Rel.Sign.reverse(sign);
        }
        return new Rel(left,new Rel.Break(color,sign1,right,false));
    }
    public Set toInt(){
        if(sign== Rel.Sign.NE){
            Interval int1=new Interval(Inf.of(false),a,true,true);
            Interval int2=new Interval(a,Inf.of(true),true,true);
            //TODO
        }
        if(sign== Rel.Sign.EQ){
            return new
        }
    }
    public enum Side{
        LEFT,RIGHT
    }
}
