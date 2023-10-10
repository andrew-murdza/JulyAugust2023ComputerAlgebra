package actions.simplification.basic.complete;

import Relation.Eq;
import actions.StepsResult;
import actions.simplification.StepGen;
import actions.structure.Step;
import expression.Expression;
import expression.compound.Term;
import util.Helper;
import util.List;

public class CancelTerms implements StepGen<Expression, Expression> {

    @Override
    public StepsResult<Expression> createSteps(Expression e) {
        List<List<Term>> lists= e.termLists().fill(p->new List<>(p.terms));
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
            newList=newList.filter(p->Helper.sum(p.fill(Expression::coef)).isZero().isFalse());
            commonTerms.push(newList);
        }
        Expression f=e;
        for(List<List<Term>> list1:commonTerms){
            List<List<Term>> list3=list1.copy();
            for(int i=0;i<list1.size();i++){
                e=e.setGroup(list3.get(i),i);
                f=f.removeTermsAndUpdate(list1.get(i),list1);
            }
        }
        Step step1=new Step("Select each group of adjacent terms which cancel out",e.toStringDoubleDollar());
        Step step2=new Step("Cancel out all of those terms",new Eq(f,e).toStringDoubleDollar());
        return new StepsResult<>(f,new Step("Cancel terms",step1,step2));
    }
}
