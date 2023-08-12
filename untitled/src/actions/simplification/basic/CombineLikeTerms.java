package actions.simplification.basic;

import Relation.Eq;
import actions.structure.Step;
import expression.Expression;
import expression.compound.Term;
import exs.Example;
import util.Helper;

import java.util.ArrayList;
import java.util.List;

public class CombineLikeTerms {
    public Example createEx(Expression e){
        List<List<Term>> lists=Helper.fill(e.termLists(),p->new ArrayList<>(p.terms));
        List<List<List<Term>>> commonTerms=new ArrayList<>();//TermList, Common Term var part, list of elements with
        //that type
        for(List<Term> list:lists){
            List<List<Term>> newList=new ArrayList<>();
            outer:for(Term term:list){
                for(List<Term> termList:newList){
                    if(termList.size()>0&&termList.get(0).commonTerms(term)){
                        termList.add(term);
                        break outer;
                    }
                }
                newList.add(Helper.asList(term));
            }
            newList=Helper.filter(newList,p->p.size()>1);
            commonTerms.add(newList);
        }
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
        List<Step> substeps=new ArrayList<>();
        Expression f=e;
        for(List<List<Term>> list1:commonTerms){
            for(int i=0;i<list1.size();i++){
                List<Term> list2=list1.get(i);
                e=e.setGroup(list2,i);
                int finalI = i;
                Step substep=new Step(Helper.listExp(Helper.fill(list2, p->p.setColor(finalI))));
                Step substep1=new Step("Identify the coefficients and variable part. If there is no variable part, then put 1");
                int n=list2.size();
                substep1.str=Helper.textBoxArray(Helper.add(Helper.fill(Helper.range(n),p->Helper.nthWordNoCapital(p)+" coefficient"),
                                "variable part"),
                        Helper.add(Helper.fill(list2,p->p.coef().toString()),list2.get(0).getVarPart().toString()),
                        Helper.duplicate(n+1,false));
                Expression newCoef=Helper.sum(Helper.fill(list2, Expression::coef));
                Step substep2=new Step("Find the new coefficient",Helper.textBoxArray("new coefficient",
                        newCoef.toString(),false));
                Expression newTerm=newCoef.times(list2.get(0).getVarPart());
                Step substep3=new Step("Find the new term",Helper.textBoxArray("new term",
                        newTerm.toString(),false));
                //Replace common terms with a single term
                Expression g=f.replaceWithSingle(list2,newTerm).setColor(i);
                Step substep4=new Step("Substitute changes",new Eq(f,g).toStringDoubleDollar());
                f=g;
                substep.substeps=Helper.asList(substep1,substep2,substep3,substep4);
                substeps.add(substep);
            }
        }
        Step step1=new Step("Select each of group of common terms",e.toStringDoubleDollar());
        Step step2=new Step("For each group of selected terms combine them by adding their coefficients",new Eq(e,f).toStringDoubleDollar());
        return new Example(prompt,question,Helper.asList(step1,step2));
    }
}
