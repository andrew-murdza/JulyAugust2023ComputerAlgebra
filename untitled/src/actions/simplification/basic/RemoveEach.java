package actions.simplification.basic;

import expression.Expression;
import util.Helper;

import util.List;

public abstract class RemoveEach extends ApplyToEach<Expression> {

    public RemoveEach(String caseStr) {
        super("Remove all cases of "+ Helper.blueText(caseStr),
                "Select all cases of "+Helper.blueText(caseStr), "Remove "
                        +Helper.blueText("those cases"));
    }

    @Override
    public Expression editExpression(List<Expression> list, Expression e) {
        return e.remove(list);
    }
}
