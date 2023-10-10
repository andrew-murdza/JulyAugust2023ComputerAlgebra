package src.actions.simplification.propexp;

import actions.simplification.basic.complete.RemoveEach;
import expression.Expression;
import expression.dexp.One;
import util.List;

public class PowOne extends RemoveEach {
    public PowOne() {
        super("power of 1");
    }
    @Override
    public List<Expression> genList(Expression e) {
        return e.pows().filter(p->p instanceof One);
    }
}
