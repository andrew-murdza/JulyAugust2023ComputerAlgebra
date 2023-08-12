package actions.simplification.propexp;


import Relation.Eq;
import actions.structure.Step;
import color.Color;
import expression.Expression;
import expression.Var;
import expression.compound.PowExp;
import exs.Example;
import lombok.AllArgsConstructor;
import util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public abstract class ProcessWithFormula<T extends Expression> {
    public Eq formula1;
    public String name;
    public abstract List<T> genList(Expression e);
    public abstract List<Var> vars();
    public abstract List<Expression> genParams(T t);
    public List<Integer> shifts(){
        return Helper.duplicate(vars().size(),0);
    }
    public Expression processLeft(Expression e, T t){
        return e;
    }
    public Expression processRight(Expression e, T t){
        return e;
    }
    public Example createEx(Expression e){
        String formulaStr=formula1.toStringSingleDollarBigText();
        List<T> list=genList(e);
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toStringDoubleDollar());
//        Step step0=new Step("Rewrite nth roots using "+formula);
        Step step1=new Step("Select all cases of "+name+"s");//Rewrite fractions with variables using formula for title
        Expression f=e.setGroup(list,0);
        step1.str=f.toStringDoubleDollar();
        Step step2=new Step("Rewrite each "+name+" using "+formulaStr);
        List<Step> substeps=new ArrayList<>();
        List<Var> vars=Helper.fillI(vars(),(p,i)->(Var)p.setColor(i+shifts().get(i)));
        Expression g=e;
        for(T t:list){
            Step step21=new Step(t.toStringSingleDollarBigText());
            List<Expression> params=Helper.fillI(genParams(t), (p,i)->p.setColor(i+shifts().get(i)));
            Expression left=processLeft(formula1.left,t).sub(vars,params);
            Expression right=processRight(formula1.right,t).sub(vars,params);
            Step step211=new Step("Identify "+Helper.listExp(vars), Helper.textBoxArray(
                    Helper.fill(vars,p->p.name),params,Helper.duplicate(vars.size(),true)));
            Step step212=new Step("Rewrite using "+formulaStr,"$$"+left+"="+right+"$$");
            Step step213=new Step("substitute changes",new Eq(g.replace(t,left),g.replace(t,right.textBox())).toStringDoubleDollar());
            g=g.replace(t,right.removeColor());
            step21.substeps=Helper.asList(step211,step212,step213);
            substeps.add(step21);
        }
        step2.substeps=substeps;
        return new Example(prompt,question,Helper.asList(step1,step2));
    }
}
