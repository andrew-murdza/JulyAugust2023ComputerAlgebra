package actions.simplification.basic;

import Relation.Eq;
import actions.structure.Step;
import expression.Expression;
import expression.dexp.One;
import exs.Example;
import util.Helper;

import java.util.List;

public class MultDivOne {
    public Example createEx(Expression e){
        List<Expression> list= Helper.filter(e.factors(), p->p instanceof One);
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
        Step step1=new Step("Select all cases of "+Helper.blueText("multiplying or dividing by 1"));
        Expression f=e.setGroup(list,0);
        step1.str=f.toStringDoubleDollar();
        Step step2=new Step("Because "+Helper.blueText("adding or subtracting zero")+" has no effect on the expression,"+
                Helper.goldText(" remove ")+"these terms");
        step2.str=new Eq(f,e.remove(list)).toStringDoubleDollar();
        return new Example(prompt,question,Helper.asList(step1,step2));
    }
}
