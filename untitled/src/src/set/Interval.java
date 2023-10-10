package src.set;

import expression.Expression;
import lombok.AllArgsConstructor;
import set.Set;

@AllArgsConstructor
public class Interval extends Set {
    public Expression a;
    public Expression b;
    public boolean openLeft;
    public boolean openRight;
}
