package actions.simplification.basic.complete;

import Relation.Eq;
import actions.StepsResult;
import actions.simplification.StepGen;
import actions.structure.DropDown;
import actions.structure.Step;
import color.Color;
import expression.Expression;
import expression.compound.FactorGroup;
import util.Helper;
import util.List;

public class CombineNegFactors implements StepGen<Expression,Expression> {
    String str=Helper.greenText("odd")+" or "+Helper.goldText("even");
    @Override
    public StepsResult<Expression> createSteps(Expression e) {
        Expression f=e;//need to update in loop
        List<FactorGroup> list=e.factorGroups().filter(p->(e.numFracFactors().contains(
                p.factors.get(0))?p.factors:p.factors.subList(1)).anyTrue(q->q.numMinusSigns()>0));
        List<Step> substeps=new List<>();
        for(int i=0;i<list.size();i++){
            FactorGroup group=list.get(i);
            List<Step> substeps1=new List<>();
            int n=group.numMinusSigns();
            boolean b=n%2==0;
            substeps1.push(new Step("Count the number of negative signs",Helper.textBoxArray("number of negative signs",
                    Integer.toString(n),false)));
            Step step=new Step("Is the number of negative signs "+str+"?","{dropDown}");
            step.dropdowns=List.of(new DropDown("Is the number of negative signs "+str+"?",b?1:0,"odd","even"));
            Expression e1=group.toExpression();
            Expression e2=e1.removeMinusSigns();
            Expression e3=b?e2:e2.negateWithColor(Color.GREEN);
            Expression e4=b?e2:e2.negate();
            substeps1.push(step);
            Step step1=new Step("Should we cancel all the $-$ signs or replace them with a single negative sign");
            step1.dropdowns=List.of(new DropDown("cancel or replace?",b?0:1,
                    "cancel all $-$ signs","replace all $-$ signs with a single $-$ sign"));
            substeps1.push(step1);
            substeps1.push(new Step((b?Helper.goldText("cancel"):Helper.greenText("replace"))+" all negative signs"
                    +(b?"":" with "+Helper.greenText("a single negative sign")),new Eq(e1,e3).toStringDoubleDollar()));

            Expression g=f.replace(group,e3);
            substeps1.push(new Step("substitute changes", new Eq(f.setGroupFactors(i,group),g).toStringDoubleDollar()));
            f=f.replaceAndUpdateFactorGroup(group.toExpression(),e3,list);
            substeps.push(new Step(group.toStringSingleDollar(),substeps1));
        }
        Expression f1=e.setGroupFactors(list);
        Step step11=new Step("Select each group of adjacent factors with negative signs to combine",f1.toStringDoubleDollar());
        Step step12=new Step("Simplify each group based on whether the number of negative signs is "+str,substeps);
        return new StepsResult<>(f,new Step("combine negative signs",step11,step12));
    }
}
