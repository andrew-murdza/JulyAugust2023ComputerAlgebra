package actions.simplification.propexp;

import Relation.Eq;
import expression.Expression;
import expression.Var;
import expression.compound.PowExp;
import util.Helper;

import util.List;

public class DoublePow extends ProcessWithFormula<PowExp> {
    public DoublePow(){
        super(new Eq(vars1().get(0).pow(vars1().get(1)).pow(vars1().get(2)),vars1().get(0).pow(vars1().get(1).times(vars1().get(2)))),"double power");
    }
    public static List<Var> vars1(){
        return Helper.vars("a","n","m");
    }

    @Override
    public List<PowExp> genList(Expression e) {
        return Helper.filterOutInner(e.powexps().filter(p->p.base instanceof PowExp)).fill(p->(PowExp)p);
    }

    @Override
    public List<Var> vars() {
        return vars1();
    }

    @Override
    public List<Expression> genParams(PowExp powExp) {
        return List.of(((PowExp)powExp.base).base,((PowExp)powExp.base).pow,powExp.pow);
    }
}

