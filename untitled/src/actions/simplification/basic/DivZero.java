package actions.simplification.basic;

import actions.structure.Case;
import actions.structure.InnerCase;
import actions.structure.Step;
import expression.Expression;
import exs.Example;
import util.Helper;

import java.util.List;

public class DivZero {
    public Example createEx(Expression e){
        List<Expression> list=Helper.filterGen(e.denomFactors(),Expression::isZero);
        String prompt="Simplify";
        Example.Question question=new Example.Question(e.toString());
        Step step1=new Step("Determine if the expression has division by zero");
        step1.str="{innerCases}";
        Case case1=new Case("The expression is <span class='gold'>undefined</span>",
                "The expression has <span class='blue'>division by 0</span>");
        Case case2=new Case("Try another way of simplifying",
                "The expression doesn't have <span class='blue'>division by 0</span>");
        step1.innerCases=Helper.asList(new InnerCase(Helper.asList(case1,case2),list.isEmpty()?1:0));
        if(!list.isEmpty()){
            step1.strAfter="$$"+ e +"=\\got{undefined}$$";
        }
        return new Example(prompt,question,step1);
    }
}
