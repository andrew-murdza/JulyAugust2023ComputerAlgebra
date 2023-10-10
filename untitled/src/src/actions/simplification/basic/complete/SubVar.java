package src.actions.simplification.basic.complete;

import actions.StepsResult;
import actions.simplification.StepGen;
import actions.structure.Step;
import expression.Expression;
import expression.Var;
import util.Helper;
import util.List;

public class SubVar implements StepGen<Expression,Expression> {
    public StepsResult<Expression> createSteps(Expression e){
        List<Var> vars=e.vars();
        List<Var> vars1=e.varExps();
        int n=vars.size();
        List<Step> substeps=new List<>();
        substeps.push(new Step("Identify each value which a known value",Helper.textBoxArray(List.range(n)
                .fill(p->Helper.nthWordNotCapital(p)+" known variable"),vars.fill(p->p),List.duplicate(n,false))));
        substeps.push(new Step("Identify the value of each known variable",Helper.textBoxArray(vars.fill(p->p.name),
                vars.fill(p->p.val),List.duplicate(n,true))));
        Expression f=e;
        Expression g=e;
        for(int i=0;i<vars.size();i++){
            int finalI = i;
            List<Var> relevantExps=vars1.filter(p->p.equals(vars.get(finalI)));
            f=f.setGroup(relevantExps,i);
            g=g.replace(relevantExps,p->((Var)p).val.setColor(finalI));
        }
        substeps.push(new Step("For each known variable, select all cases of the known variable in the group for that" +
                " variable",f.toStringDoubleDollar()));
        substeps.push(new Step("Substitute the value of each known variable"));
        return new StepsResult<>(g,new Step("Substitute known variables",substeps));
    }
}
