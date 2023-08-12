package actions.simplification.basic;

import actions.structure.Step;
import color.Color;
import expression.Expression;
import expression.compound.FactorGroup;
import expression.dexp.Zero;
import exs.Example;
import util.Helper;

import java.util.List;

public class MultZero {
    public Example createEx(Expression e){
        List<FactorGroup> list= Helper.filter(e.factorGroups(),
                p->Helper.anyTrue(p.factors, q->q instanceof Zero)&&Helper.allTrueGen(p.factors, Expression::isFinite));
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
        Step step1=new Step("Select all cases of "+Helper.blueText("multiplying a finite expression by 0"));
        step1.str=e.setGroupFactors(list,0).toStringDoubleDollar();
        Step step2=new Step("Replace all cases of "+Helper.blueText("multiplying a finite expression by 0")+" with"
                +Helper.goldText("0"));
        step2.str=e.replaceForEach(list,p->new Zero().setColor(Color.GOLD)).toStringDoubleDollar();
        return new Example(prompt,question,Helper.asList(step1,step2));
    }
}
