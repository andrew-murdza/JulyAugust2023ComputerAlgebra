package actions.simplification.basic;

import Relation.Eq;
import actions.structure.Step;
import color.Color;
import expression.Expression;
import expression.compound.FactorGroup;
import exs.Example;
import util.Helper;
import util.List;

public class MultiplyFractions {
    public Example createEx(Expression e){
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
        List<FactorGroup> groups=e.factorGroups().filter(p->p.factors.size()>1&&p.factors.anyTrue(q->!q.denomFactors().isEmpty()));
        groups=Helper.filterOutInnerFactorGroup(groups);
        List<Step> substeps=new List<>();
        Expression f=e;
        Expression g=e;
        List<Expression> newFracs=new List<>();
        for(int i=0;i<groups.size();i++){
            FactorGroup group=groups.get(i);
            FactorGroup groupTop=new FactorGroup(group.topFactors());
            FactorGroup groupBot=new FactorGroup(group.botFactors());
            Expression top=groupTop.toExpression();
            Expression bot=groupBot.toExpression();
            f=f.setGroupFactors(2*i,groupTop).setGroupFactors(2*i+1,groupBot);
            Expression enew=e.setColor(Color.GREY).setGroupFactors(2*i,groupTop).setGroupFactors(2*i+1,groupBot);
            for(int j=0;j<i;j++){
                enew=enew.replaceWithSingle(groups.get(j).factors,newFracs.get(j));
            }
            Expression h=top.div(bot);
            Expression fnew=enew.replaceWithSingle(groups.get(i).factors,h);
            newFracs.push(h);
            Step step21=new Step(g.toStringSingleDollarBigText());
            Step step211=new Step("Find the new numerator and denominator",Helper.textBoxArrayStr(
                    List.of("new numerator","new denominator"),List.of(top.toString(),bot.toString()),List.duplicate(2,false)));
            Step step212=new Step("determine the new fraction",h.toStringDoubleDollar());
            Expression g1=g.replaceWithSingle(group.factors,h);
            Step step213=new Step("substitute changes", new Eq(enew,fnew).toStringDoubleDollar());
            step21.substeps=List.of(step211,step212,step213);
            substeps.push(step21);
        }
        Step step0=new Step("Select each case of "+Helper.blueText("fractions being multiplied"),f.toStringDoubleDollar());
        Step step1=new Step("For each factor group, create a group for its numerator factors and a group for its denominator factors"
                ,new Eq(e,f).toStringDoubleDollar());
        Step step2=new Step("Simplify each factor group by making a single fraction");
        step2.substeps=substeps;
        return new Example(prompt,question,List.of(step0,step1,step2));
    }
}
