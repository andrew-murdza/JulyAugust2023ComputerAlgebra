package actions.simplification.propexp;

import Relation.Eq;
import color.Color;
import expression.Expression;
import expression.Var;
import expression.compound.Sqrt;
import expression.dexp.Int;
import util.Helper;
import util.List;

public class SqrtRootToPow extends ProcessWithFormula<Sqrt> {
    public SqrtRootToPow(){
        super(new Eq(vars1().get(0).sqrt().setColor(Color.BLUE),
                vars1().get(0).pow(Int.of(1).div(Int.of(2))).setColor(Color.BLUE)),"square root");
    }
    public static List<Var> vars1(){
        return Helper.vars("a");
    }

    @Override
    public List<Sqrt> genList(Expression e) {
        return e.sqrts();
    }

    @Override
    public List<Var> vars() {
        return vars1();
    }

    @Override
    public List<Expression> genParams(Sqrt sqrt) {
        return List.of(sqrt.inside);
    }
}
