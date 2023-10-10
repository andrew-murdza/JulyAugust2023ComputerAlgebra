package src.actions.simplification.basic.complete;

import actions.simplification.basic.complete.RemoveEach;
import expression.Expression;
import expression.dexp.One;
import util.List;

public class MultDivOne extends RemoveEach {
    public MultDivOne() {
        super("multiplying or dividing by 1");
    }

    public List<Expression> genList(Expression e) {
        return e.factors().fillCond(p->p instanceof One, p->p);
    }
}
