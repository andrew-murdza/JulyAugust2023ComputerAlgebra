package actions.simplification.propexp;


import Relation.Eq;
import actions.structure.Step;
import expression.Expression;
import expression.Var;
import exs.Example;
import lombok.AllArgsConstructor;
import util.Helper;
import util.List;

@AllArgsConstructor
public abstract class ProcessWithFormula<T extends Expression> {
    public Eq formula1;
    public String name;
    public abstract List<T> genList(Expression e);
    public abstract List<Var> vars();
    public abstract List<Expression> genParams(T t);
    public List<Integer> shifts(){
        return List.duplicate(vars().size(),0);
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
        List<Step> substeps=new List<>();
        List<Var> vars=vars().fillI((i,p)->(Var)p.setColor(i+shifts().get(i)));
        Expression g=e;
        for(T t:list){
            Step step21=new Step(t.toStringSingleDollarBigText());
            List<Expression> params=genParams(t).fillI((i,p)->p.setColor(i+shifts().get(i)));
            Expression left=processLeft(formula1.left,t).sub(vars,params);
            Expression right=processRight(formula1.right,t).sub(vars,params);
            Step step211=new Step("Identify "+Helper.listExp(vars), Helper.textBoxArray(
                    vars.fill(p->p.name),params,List.duplicate(vars.size(),true)));
            Step step212=new Step("Rewrite using "+formulaStr,"$$"+left+"="+right+"$$");
            Step step213=new Step("substitute changes",new Eq(g.replace(t,left),g.replace(t,right.textBox())).toStringDoubleDollar());
            g=g.replace(t,right.removeColor());
            step21.substeps=List.of(step211,step212,step213);
            substeps.push(step21);
        }
        step2.substeps=substeps;
        return new Example(prompt,question,List.of(step1,step2));
    }
}
