package Relation;

import color.Color;
import expression.Expression;
import util.Helper;

public class Eq extends Rel{

    public Eq(Expression left, Expression right, Color color){
        super(left, Helper.asList(new Break(color,Sign.EQ,right,false)));
    }
    public Eq(Expression left, Expression right){
        this(left,right,Color.INHERIT);
    }
}
