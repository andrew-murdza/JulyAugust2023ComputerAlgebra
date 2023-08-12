package actions.simplification.basic;

import Relation.Eq;
import actions.structure.Step;
import expression.Expression;
import expression.compound.Term;
import exs.Example;
import util.Helper;

import java.util.ArrayList;
import java.util.List;

public class CancelTerms {
    public Example createEx(Expression e){
        List<List<Term>> lists= Helper.fill(e.termLists(), p->new ArrayList<>(p.terms));
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
            newList=Helper.filter(newList,p->Helper.sum(Helper.fill(p,Expression::coef)).isZero().isFalse());
            commonTerms.add(newList);
        }
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
        Expression f=e;
        for(List<List<Term>> list1:commonTerms){
            for(int i=0;i<list1.size();i++){
                List<Term> list2=list1.get(i);
                e=e.setGroup(list2,i);
                f=f.remove(list2);
            }
        }
        Step step1=new Step("Select each group of adjacent terms which cancel out",e.toStringDoubleDollar());
        Step step2=new Step("Cancel out all of those terms",new Eq(f,e).toStringDoubleDollar());
        return new Example(prompt,question,Helper.asList(step1,step2));
    }
}
