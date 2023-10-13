package expression.compound;

import Relation.Rel;
import expression.Expression;
import lombok.AllArgsConstructor;
import util.List;

@AllArgsConstructor
public class PiecewiseExp extends Expression {
    List<PiecewisePair> pairs;

    @Override
    public String toStringHelper() {
        String str="\\begin{cases}";
        for(PiecewisePair pair:pairs){
            str+=pair.e+"&\\text{if }"+pair.rel;
        }
        return str+"\\end{cases}";
    }

    @AllArgsConstructor
    public static class PiecewisePair{
        public Expression e;
        public Rel rel;
    }
}
