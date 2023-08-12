package actions.simplification.basic;

import actions.structure.Case;
import actions.structure.InnerCase;
import actions.structure.Step;
import color.Color;
import expression.Expression;
import expression.compound.FactorGroup;
import expression.dexp.Int;
import exs.Example;
import util.Helper;

import java.util.ArrayList;
import java.util.List;

public class CombineNegFactors {
    public Example createEx(Expression e){
        List<FactorGroup> list= Helper.filter(e.factorGroups(),
                p->Helper.anyTrue(Helper.fromIndex(p.factors,1), q->q instanceof Int i&&i.i<0));
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
        Step step1=new Step("Select each group of adjacent factors with a at least one negative sign not in the front of the expression");
        step1.str=e.setGroupFactors(list,0).toStringDoubleDollar();
        Step step2=new Step("For each group of adjacent factors determine whether the number of factors with negative signs is "
                +Helper.greenText("odd")+" or "+Helper.orangeText("even"));
        List<Step> subSteps=new ArrayList<>();
        List<FactorGroup> newGroups=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            FactorGroup group=list.get(i);
            String exp=group.toExpression().setColor(Color.BLUE).toString();
            Step step=new Step("$$\\bigtext{"+exp+"}$$");
            int n=Helper.countMatches(group.factors,q->q instanceof Int j&&j.i<0);
            Step step21=new Step("Count the number of negative signs");
            step21.str=Helper.textBoxArray("number of negative signs", Integer.toString(n),false);
            Case case1=new Case("<span class='gold'>Cancel</span> all of those negative signs",
                    "There is an <span class='gold'>even number</span> of negative signs");
            Case case2=new Case("<span class=\"green\">Replace</span> all" +
                    " of those negative signs with<span class=\"green\"> a single negative sign</span>",
                    "There is an <span class='green'>odd number</span> of negative" +
                            " signs");
            Step step22=new Step("Is the number of negative signs <span class='green'>odd</span> or <span class='gold'>even</span>?");
            step22.str="{innerCases}";
            step22.innerCases=Helper.asList(new InnerCase(Helper.asList(case1,case2),n%2));
            FactorGroup newGroup=new FactorGroup(Helper.fill(group.factors,p->p instanceof Int j&&j.i<0?Int.of(-j.i):p));
            newGroups.add(newGroup);
            step22.strAfter="$$"+exp+"="+newGroup.toExpression().setColor(Color.BLUE).toString()+"$$";
            step.substeps=Helper.asList(step21,step22);
        }
        Step finalStep=new Step("substitute changes");
        finalStep.str=e.replaceForEachI(list,(i,p)->newGroups.get(i).toExpression()).toStringDoubleDollar();
        subSteps.add(finalStep);
        step2.substeps=subSteps;
//        step2.str=e.replaceForEach(list,p->new Zero().setColor(Color.GOLD)).toStringDoubleDollar();
        return new Example(prompt,question,Helper.asList(step1,step2));
    }
}
