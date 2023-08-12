package actions.simplification.propexp;

import Relation.Eq;
import actions.structure.Step;
import expression.Expression;
import expression.dexp.One;
import exs.Example;
import util.Helper;

import java.util.List;

public class PowOne {
    public Example createEx(Expression e){
        List<Expression> list= Helper.filter(e.pows(), p->p instanceof One);
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
        Step step1=new Step("Identify all "+Helper.blueText("powers of $1$")+" by selecting those" +
                " powers");
        Expression f=e.setGroup(list,0);
        step1.str=f.toStringDoubleDollar();
        Step step2=new Step("Because "+Helper.blueText("raising to the power of $1$")+" has no effect on the expression,"+
                Helper.goldText(" remove ")+"those powers");
        step2.str=new Eq(f,e.remove(list)).toStringDoubleDollar();
        return new Example(prompt,question,Helper.asList(step1,step2));
    }
}
