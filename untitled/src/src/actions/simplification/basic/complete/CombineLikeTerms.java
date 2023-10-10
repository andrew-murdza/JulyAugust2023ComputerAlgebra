package src.actions.simplification.basic.complete;

import Relation.Eq;
import actions.StepsResult;
import actions.simplification.StepGen;
import actions.structure.Step;
import expression.Expression;
import expression.compound.Term;
import util.Helper;
import util.List;

public class CombineLikeTerms implements StepGen<Expression, Expression> {
    @Override
    public StepsResult<Expression> createSteps(Expression e) {
        List<List<Term>> lists=e.termLists().fill(p->new List<>(p.terms));
        List<List<List<Term>>> commonTerms=new List<>();//TermList, Common Term var part, list of elements with
        //that type
        for(List<Term> list:lists){
            List<List<Term>> newList=new List<>();
            outer:for(Term term:list){
                for(List<Term> termList:newList){
                    if(!termList.isEmpty() &&termList.get(0).commonTerms(term)){
                        termList.push(term);
                        break outer;
                    }
                }
                newList.push(List.of(term));
            }
            newList=newList.filter(p->p.size()>1);
            commonTerms.push(newList);
        }
        List<Step> substeps=new List<>();
        Expression f=e;
        for(List<List<Term>> list1:commonTerms){
            List<List<Term>> list3=list1.copy();
            for(int i=0;i<list1.size();i++){
                List<Term> list2=list1.get(i);
                e=e.setGroup(list3.get(i),i);
                int finalI = i;
                Step substep=new Step(Helper.listExp(list2.fill(p->p.setColor(finalI))));
                Step substep1=new Step("Identify the coefficients and variable part. If there is no variable part, then put 1");
                int n=list2.size();
                substep1.str=Helper.textBoxArrayStr(List.range(n).fill(p->Helper.nthWordNotCapital(p)+" coefficient").add(
                                "variable part"),
                        list2.fill(p->p.coef().toString()).add(list2.get(0).getVarPart().toString()),
                        List.duplicate(n+1,false));
                Expression newCoef=Helper.sum(list2.fill(Expression::coef));
                Step substep2=new Step("Find the new coefficient",Helper.textBoxArray("new coefficient",
                        newCoef.toString(),false));
                Expression newTerm=newCoef.times(list2.get(0).getVarPart());
                Step substep3=new Step("Find the new term",Helper.textBoxArray("new term",
                        newTerm.toString(),false));
                //Replace common terms with a single term
                Expression g=f.replaceWithSingleAndUpdate(list2,newTerm.setColor(i),list1);
                Step substep4=new Step("Substitute changes",new Eq(f,g).toStringDoubleDollar());
                f=g.removeColor();
                substep.substeps=List.of(substep1,substep2,substep3,substep4);
                substeps.push(substep);
            }
        }
        Step step1=new Step("Select each of group of common terms",e.toStringDoubleDollar());
        Step step2=new Step("For each group of selected terms combine them by adding their coefficients",
                new Eq(e,f).toStringDoubleDollar(),substeps);
        return new StepsResult<>(f,new Step("Combine like terms",step1,step2));
    }
}
