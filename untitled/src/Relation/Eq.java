package Relation;

import color.Color;
import expression.Expression;
import util.List;

public class Eq extends Rel{

    public Eq(Expression left, Expression right, Color color){
        super(left, List.of(new Break(color,Sign.EQ,right,false)));
    }
    public Eq(Expression left, Expression right){
        this(left,right,Color.INHERIT);
    }
}
