package expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;
import util.List;

@AllArgsConstructor
public class FactorList extends Expression {
    List<Expression> es;

    @Override
    public String toStringHelper() {
        String str="";
        for(Expression e:es){
            str+=e.terms().size()>1?"("+e+")":e;
        }
        return str.isEmpty()?"1":str;
    }
}
