package src.expression.compound;

import expression.Expression;
import expression.compound.Term;
import lombok.AllArgsConstructor;
import util.List;

@AllArgsConstructor
public class TermList extends Expression {
    public List<Term> terms;

    @Override
    public String toStringHelper() {
        String str="";
        for(int i=0;i<terms.size();i++){
            str+=(terms.get(i).isAdded?i>0?"+":"":"-")+ terms.get(i);
        }
        return str.isEmpty()?"0":str;
    }
}
