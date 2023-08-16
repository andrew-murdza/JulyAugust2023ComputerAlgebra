package actions.simplification.basic;

import color.Color;
import expression.Expression;
import expression.compound.FactorGroup;
import expression.dexp.Zero;
import util.Helper;

import util.List;

public class MultZero extends ApplyToEach<Expression>{
    public static String caseStr="all cases of "+Helper.blueText("something multiplied by 0");
    public MultZero() {
        super("replace "+caseStr+" with "+Helper.goldText("0"), "Select "+caseStr,
                "replace "+Helper.blueText("those cases")+" with "+Helper.goldText("0"));
    }

    @Override
    public List<Expression> genList(Expression e) {
        return e.factorGroups().filter(
                p -> p.numFactors().anyTrue(q -> q instanceof Zero)&&p.factors.size()>1
                        &&!p.denomFactors().anyTrue(q->q instanceof Zero)
                        && p.factors.allTrueGen(Expression::isFinite)).fill(FactorGroup::toExpression);
    }

    @Override
    public Expression editExpression(List<Expression> list, Expression e) {
        return e.replace(list,p->new Zero().setColor(Color.GOLD));
    }
}
