package expression.compound;

import color.Color;
import expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Term extends Expression{
    public Expression e;//Might be ExpressionNode or something like that
    public Color color;
    public boolean isAdded;
    public Term(Expression e, boolean isAdded){
        this(e,Color.INHERIT,isAdded);
    }
}
