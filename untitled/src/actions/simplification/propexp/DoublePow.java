package actions.simplification.propexp;

import Relation.Eq;
import expression.Expression;
import expression.Var;
import expression.compound.PowExp;
import util.Helper;

import java.util.List;

public class DoublePow extends ProcessWithFormula<PowExp> {
    public DoublePow(){
        super(new Eq(vars1().get(0).pow(vars1().get(1)).pow(vars1().get(2)),vars1().get(0).pow(vars1().get(1).times(vars1().get(2)))),"double power");
    }
    public static List<Var> vars1(){
        return Helper.vars("a","n","m");
    }

    @Override
    public List<PowExp> genList(Expression e) {
        return Helper.fill(Helper.filterOutInner(Helper.filter(e.powexps(),p->p.base instanceof PowExp)),p->(PowExp)p);
    }

    @Override
    public List<Var> vars() {
        return vars1();
    }

    @Override
    public List<Expression> genParams(PowExp powExp) {
        return Helper.asList(((PowExp)powExp.base).base,((PowExp)powExp.base).pow,powExp.pow);
    }
}

