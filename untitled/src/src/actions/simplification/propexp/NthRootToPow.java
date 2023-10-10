package src.actions.simplification.propexp;

import Relation.Eq;
import actions.simplification.propexp.ProcessWithFormula;
import color.Color;
import expression.Expression;
import expression.Var;
import expression.compound.NthRoot;
import expression.dexp.Int;
import util.Helper;
import util.List;

public class NthRootToPow extends ProcessWithFormula<NthRoot> {
    public NthRootToPow(){
        super(new Eq(vars1().get(0).root(vars1().get(1)).setColor(Color.BLUE),
                vars1().get(0).pow(Int.of(1).div(vars1().get(1))).setColor(Color.BLUE)),"nth root");
    }
    public static List<Var> vars1(){
        return Helper.vars("a","n");
    }
    public List<Integer> shifts(){
        return List.of(0,1);
    }

    @Override
    public List<NthRoot> genList(Expression e) {
        return e.nthRoots();
    }

    @Override
    public List<Var> vars() {
        return vars1();
    }

    @Override
    public List<Expression> genParams(NthRoot root) {
        return List.of(root.inside,root.n);
    }
}