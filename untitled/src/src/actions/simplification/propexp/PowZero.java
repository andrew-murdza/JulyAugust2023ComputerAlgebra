package src.actions.simplification.propexp;

import actions.simplification.basic.complete.ApplyToEach;
import color.Color;
import expression.Expression;
import expression.compound.PowExp;
import expression.dexp.One;
import expression.dexp.Zero;
import util.Helper;
import util.List;

public class PowZero extends ApplyToEach<PowExp> {
    public static String caseStr="all cases of "+Helper.blueText("something to the power of 0");
    public PowZero() {
        super("replace "+caseStr+" with "+Helper.goldText("1"), "Select "+caseStr,
                "replace "+Helper.blueText("those cases")+" with "+Helper.goldText("1"));
    }
    @Override
    public List<PowExp> genList(Expression e) {
        return e.powexps().filter(p->p.pow instanceof Zero&&p.base.isNonZero().isTrue()&&p.base.isFinite().isTrue());
    }

    @Override
    public Expression editExpression(List<PowExp> list, Expression e) {
        return e.replace(list,p->new One().setColor(Color.GOLD));
    }
}
