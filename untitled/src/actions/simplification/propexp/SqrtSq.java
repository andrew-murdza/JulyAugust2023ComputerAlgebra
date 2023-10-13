package actions.simplification.propexp;

import actions.simplification.basic.complete.ApplyToEach;
import expression.Expression;
import expression.compound.NthRoot;
import expression.compound.PowExp;
import util.Helper;

import util.List;

public class SqrtSq extends ApplyToEach<PowExp> {

    public static String caseStr="all cases of "+Helper.blueText("something multiplied by 0");
    public SqrtSq() {
        super("Cancel out"+Helper.blueText("square roots being squared"),
                "Select all cases of "+Helper.blueText("a square root being squared"),
                "Cancel the square root and square in each case");
    }

    //Technically more general than square root and square
    @Override
    public List<PowExp> genList(Expression e) {
        return e.powexps().filter(p->p.base instanceof NthRoot root&&root.n.equals(p.pow));
    }

    @Override
    public Expression editExpression(List<PowExp> list, Expression e) {
        return e.replace(list,p->((NthRoot)((PowExp)p).e).e);
    }
}
