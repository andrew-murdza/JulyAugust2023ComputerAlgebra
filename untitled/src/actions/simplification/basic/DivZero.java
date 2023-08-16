package actions.simplification.basic;

import actions.simplification.HeaderWithTwoSubsteps;
import color.Color;
import expression.Expression;
import expression.UndefinedExp;
import expression.dexp.Zero;
import util.Helper;

public class DivZero extends HeaderWithTwoSubsteps {
    public static String caseStr=Helper.blueText(" division by 0");
    public static String output=Helper.goldText("undefined");
    public DivZero() {
        super("Anything including"+caseStr+" is "+output,
                "Select at least one case of "+caseStr, "The expression is "+output);
    }


    @Override
    public Expression selectExp(Expression e) {
        return e.setGroup(e.denomFactors().filter(p->p instanceof Zero),0);
    }

    @Override
    public Expression returnExp(Expression e) {
        return new UndefinedExp().setColor(Color.GOLD);
    }
}
