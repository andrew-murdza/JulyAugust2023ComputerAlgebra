package expression.compound;

import color.Color;
import expression.Expression;
import expression.dexp.Zero;
import lombok.AllArgsConstructor;
import util.List;

public class TermList extends Expression {
    public List<Term> terms;
    private TermList(List<Term> terms){
        super();
        this.terms=terms;
    }
    private TermList(List<Term> terms, Color color){
        super(color);
        this.terms=terms;
    }
    private TermList(List<Term> terms, Color color, int group){
        super(color,group);
        this.terms=terms;
    }
    public static Expression ofEs(List<Expression> es){
        return of(es.fill(Term::of));
    }
    public static Expression ofEs(Expression... es){
        return ofEs(List.of(es));
    }

    public static Expression of(Term... terms){
        return of(List.of(terms));
    }

    public static Expression of(List<Term> terms){
        if(terms.isEmpty()){
            return new Zero();
        }
        if(terms.size()==1){
            Term term=terms.get(0);
            return term.toExpression();
        }
        //NEED TO "unwind" inner TermLists with isAdded=true
        return new TermList(terms);
    }

    @Override
    public String toStringHelper() {
        String str="";
        for(int i=0;i<terms.size();i++){
            str+=(terms.get(i).isAdded?i>0?"+":"":"-")+ terms.get(i);
        }
        return str.isEmpty()?"0":str;
    }
}
