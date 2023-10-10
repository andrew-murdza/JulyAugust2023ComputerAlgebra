package src.expression;

import expression.Expression;
import lombok.AllArgsConstructor;
import set.Set;

@AllArgsConstructor
public class Var extends Expression {
    public String name;
    public Set set;
    public Expression val;

    public Var(String name){
        this.name=name;
    }
}
