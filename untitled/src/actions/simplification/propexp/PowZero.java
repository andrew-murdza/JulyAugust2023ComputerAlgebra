package actions.simplification.propexp;

import Relation.Eq;
import actions.structure.Step;
import expression.Expression;
import expression.compound.PowExp;
import expression.dexp.One;
import expression.dexp.Zero;
import exs.Example;
import util.Helper;

import java.util.List;

public class PowZero {
    public Example createEx(Expression e){
        List<PowExp> list= Helper.filter(e.powexps(),p->p.pow instanceof Zero);
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
        Step step1=new Step("Identify all cases of "+Helper.blueText("something raised to the $0$ power")+" by selecting those" +
                " expressions");
        Expression f=e.setGroup(list,0);
        step1.str=f.toStringDoubleDollar();
        Step step2=new Step("Because "+Helper.blueText("anything to the $0$ power")+" is $1$,"+
                Helper.goldText(" replace those expressions with $1$"));
        step2.str=new Eq(f,e.replace(list,p->new One())).toStringDoubleDollar();
        return new Example(prompt,question,Helper.asList(step1,step2));
    }
}
