package expression.compound;

import expression.Expression;

public class BinaryExpression extends Expression{
    public Expression e;
    public Expression f;
    public BinaryExpression(Expression e, Expression f){
        super();
        this.e=e;
        this.f=f;
    }
}
