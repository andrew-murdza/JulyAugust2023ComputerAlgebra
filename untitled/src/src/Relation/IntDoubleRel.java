package src.Relation;

import Relation.Rel;
import color.Color;
import expression.Expression;
import expression.Inf;
import expression.Var;
import set.Interval;
import set.Set;
import util.List;

public class IntDoubleRel {
    public Var x;
    public boolean openLeft;
    public boolean openRight;
    public Expression a;
    public Expression b;
    public Color colorLeft;
    public Color colorRight;
    public Rel toRel(){
        List<Rel.Break> breaks=new List<>();
        breaks.push(new Rel.Break(colorLeft,openLeft? Rel.Sign.STRLE: Rel.Sign.LE,x,false));
        breaks.push(new Rel.Break(colorRight,openRight? Rel.Sign.STRLE: Rel.Sign.LE,b,false));
        return new Rel(a,breaks);
    }
    public Set toInt(){
        Expression a1;
        boolean openLeft1;
        if(a==null){
            a1=Inf.of(false);
            openLeft1=true;
        }
        else{
            a1=a;
            openLeft1=openLeft;
        }
        Expression b1;
        boolean openRight1;
        if(b==null){
            b1=Inf.of(true);
            openRight1=true;
        }
        else{
            b1=b;
            openRight1=openRight;
        }
        return new Interval(a1,b1,openLeft1,openRight1);
    }
}
