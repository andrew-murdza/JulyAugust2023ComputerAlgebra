package expression.compound;

import color.Color;
import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Term extends UniaryExpression{
    public Term(Expression e,Color color,Color signColor,boolean isAdded,int group,int signGroup){
        super(e);
        this.color=color;
        this.signColor=signColor;
        this.isAdded=isAdded;
        this.group=group;
        this.signGroup=signGroup;
    }
    public static Term of(Expression e){
        return FactorList.of(e.getCoef()).isStrNeg().isTrue()?new Term(e.negate(),false):new Term(e,true);
    }
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
    public Expression toExpression(){
        return (isAdded?e:e.negateWithColor(signColor,signGroup)).setColor(color).setGroup(group);
    }
    public String toStringHelper(){
        return toExpression().toStringHelper();
    }
}
