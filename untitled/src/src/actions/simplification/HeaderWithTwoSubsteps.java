package src.actions.simplification;

import Relation.Eq;
import actions.StepsResult;
import actions.simplification.StepGen;
import actions.structure.Step;
import expression.Expression;
import util.List;

public abstract class HeaderWithTwoSubsteps implements StepGen<Expression,Expression> {
    public HeaderWithTwoSubsteps(String step1Title,String step11Title, String step12Title){
        this.step1Title=step1Title;
        this.step11Title=step11Title;
        this.step12Title=step12Title;
    }
    public abstract Expression selectExp(Expression e);
    public abstract Expression returnExp(Expression e);
    public String step1Title;
    public String step11Title;
    public String step12Title;
    public StepsResult<Expression> createSteps(Expression e){
        Expression f=selectExp(e);
        Expression g=returnExp(e);
        Step step11=new Step(step11Title,f.toStringDoubleDollar());
        Step step12=new Step(step12Title,new Eq(f,g).toStringDoubleDollar());
        Step step1=new Step(step1Title, List.of(step11,step12));
        step1.substeps= List.of(step11,step12);
        return new StepsResult<>(g,step1);
    }
}
