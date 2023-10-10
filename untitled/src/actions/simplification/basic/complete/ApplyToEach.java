package actions.simplification.basic.complete;

import actions.simplification.HeaderWithTwoSubsteps;
import expression.Expression;

import util.List;


public abstract class ApplyToEach<T extends Expression> extends HeaderWithTwoSubsteps {
    public ApplyToEach(String step1Title, String step11Title, String step12Title) {
        super(step1Title, step11Title, step12Title);
    }

    @Override
    public Expression selectExp(Expression e) {
        return setGroup(genList(e),e);
    }

    @Override
    public Expression returnExp(Expression e) {
        return editExpression(genList(e),e);
    }

    public abstract List<T> genList(Expression e);
    public abstract Expression editExpression(List<T> list,Expression e);
    public Expression setGroup(List<T> list,Expression e){
        return e.setGroup(list,0);
    }
}
