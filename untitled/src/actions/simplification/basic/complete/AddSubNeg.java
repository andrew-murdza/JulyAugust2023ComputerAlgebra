package actions.simplification.basic.complete;

import color.Color;
import expression.Expression;
import expression.compound.Term;
import expression.dexp.Int;
import util.Helper;

import util.List;

public class AddSubNeg extends ApplyToEach<Term> {
    public boolean added;
    public AddSubNeg(boolean added) {
        super("Replace "+genCaseStr(added)+newOp(added),
                "Select "+genCaseStr(added)+" (i.e. $\\b{--}$)",
                "Replace "+Helper.blueText("those cases")+newOp(added)+ "(i.e. $\\go{+}$)");
        this.added=added;
    }

    private static String genCaseStr(boolean isAdded){
        return "all cases of "+(isAdded?"adding":"subtracting")+"a negative";
    }
    private static String newOp(boolean isAdded){
        return " with "+Helper.goldText(isAdded?"subtraction":"addition");
    }

    @Override
    public List<Term> genList(Expression e) {
        return e.terms().filter(p -> p.e.factors().get(0) instanceof
                Int i && i.i < 0 && p.isAdded==added);
    }

    @Override
    public Expression editExpression(List<Term> list, Expression e) {
        return e.replace(list, p -> new Term(((Term) p).e.remove(((Term) p).e.factors().get(0)),
                !((Term) p).isAdded).setSignColor(Color.GOLD));
    }
    @Override
    public Expression setGroup(List<Term> list,Expression e){
        return e.setGroupSign(list,0).setGroup(list.fill(p->p.e.factors().get(0)),0);
    }
}
