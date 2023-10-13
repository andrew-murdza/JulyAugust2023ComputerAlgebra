package expression.compound;

import expression.Expression;

public class TriaryExpression extends Expression{
    Expression e;
    Expression f;
    Expression g;
    public TriaryExpression(Expression e, Expression f, Expression g){
        super();
        this.e=e;
        this.f=f;
        this.g=g;
    }
}
