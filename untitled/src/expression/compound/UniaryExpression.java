package expression.compound;

import expression.Expression;

public class UniaryExpression extends Expression {
    Expression e;
    public UniaryExpression(Expression e){
        super();
        this.e=e;
    }
}
