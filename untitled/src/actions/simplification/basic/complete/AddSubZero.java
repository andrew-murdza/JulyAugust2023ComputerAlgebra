package actions.simplification.basic.complete;

import expression.Expression;
import expression.dexp.Zero;
import util.List;

public class AddSubZero extends RemoveEach {

    public AddSubZero() {
        super("adding and subtracting 0");
    }
    @Override
    public List<Expression> genList(Expression e) {
        return e.terms().fillCond(p->p.e instanceof Zero,p->p);
    }
}
