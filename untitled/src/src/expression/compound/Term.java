package src.expression.compound;

import color.Color;
import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Term{
    public Expression e;//Might be ExpressionNode or something like that
    public Color color;
    public Color signColor;
    public boolean isAdded;
    public int group;
    public int signGroup;
    public Term setSignGroup(int group){
        return new Term(e,color,isAdded,group);
    }
    public Term setSignColor(Color color){
        return new Term(e,this.color,color,isAdded,group,signGroup);
    }
    public Term(Expression e, boolean isAdded){
        this(e,Color.INHERIT,Color.INHERIT,isAdded,-1,-1);
    }
    public Term(Expression e, Color color, boolean isAdded, int group){
        this(e,color,color,isAdded,group,-1);
    }

}
