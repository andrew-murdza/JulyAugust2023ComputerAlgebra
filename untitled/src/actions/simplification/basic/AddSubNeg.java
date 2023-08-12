package actions.simplification.basic;

import Relation.Eq;
import actions.structure.Step;
import color.Color;
import expression.Expression;
import expression.compound.Term;
import exs.Example;
import util.Helper;

import java.util.ArrayList;
import java.util.List;

public class AddSubNeg {
    public Example createEx(Expression e){
        List<Term> list= Helper.filter(e.terms(), p->p.e.numMinusFactors()%2==1);
        List<Term> plusNeg=Helper.filter(list,p->p.isAdded);
        List<Term> minusNeg=Helper.filter(list,p->!p.isAdded);
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
        String str1="adding";
        String str2="subtraction";
        List<Term> terms=plusNeg;
        List<Step> steps=new ArrayList<>();
        for(int i=0;i<2;i++){
            if(i==1){
                str1="subtracting";
                str2="addition";
                terms=minusNeg;
            }
            Step step1=new Step("Select all cases of"+Helper.blueText(str1+" a negative"));
            Expression f=e.setGroup(terms,0);
            step1.str=f.toStringDoubleDollar();
            Step step2=new Step("Replace all cases of "+Helper.blueText(str1+" a negative")+" with "+
                    Helper.goldText(str2));
            step2.str=new Eq(f,e.replace(terms, p->new Term(((Term)p).e.negate(),!((Term)p).isAdded).setColor(Color.GOLD))).toStringDoubleDollar();
            steps.addAll(Helper.asList(step1,step2));
        }
        return new Example(prompt,question,steps);
    }
}
