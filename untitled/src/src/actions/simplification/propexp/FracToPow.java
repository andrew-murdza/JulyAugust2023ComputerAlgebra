package src.actions.simplification.propexp;

import Relation.Eq;
import actions.simplification.propexp.ProcessWithFormula;
import color.Color;
import expression.Expression;
import expression.Var;
import expression.compound.Frac;
import expression.compound.PowExp;
import expression.dexp.One;
import util.Helper;
import util.List;

import java.util.function.Predicate;

public class FracToPow extends ProcessWithFormula<Frac> {
    public FracToPow(){
        super(new Eq(vars1().get(1).div(vars1().get(0).pow(vars1().get(2))),vars1().get(1).times(vars1().get(0).pow(vars1().get(2)).negate().setColor(Color.GREEN))),"fraction to write");
    }
    public static List<Var> vars1(){
        return Helper.vars("a","c","n");
    }
//    public List<Integer> shifts(){
//        return List.of(0,0,1);
//    }

    @Override
    public List<Frac> genList(Expression e) {
        Predicate<Frac> pred=p->p.num.isConst()&&p.denom instanceof Var||(p.denom instanceof PowExp)&&!p.denom.isConst();
        return Helper.filterOutInner(e.fracs().filter(pred)).fill(p->((Frac)p));
    }

    @Override
    public List<Var> vars() {
        return vars1();
    }

    @Override
    public Expression processLeft(Expression e,Frac frac){
        return frac.denom instanceof PowExp?e:(((Frac)e).num).div(((PowExp)((Frac)e).denom).base);
    }
    @Override
    public List<Expression> genParams(Frac frac) {
        return List.of(frac.denom instanceof PowExp powExp?powExp.base:frac.denom,frac.num,frac.denom instanceof PowExp powExp?powExp.pow:new One());
    }
}
