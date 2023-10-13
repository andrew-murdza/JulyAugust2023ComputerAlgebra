package expression.compound;

import color.Color;
import expression.Expression;
import expression.dexp.One;
import util.List;

public class FactorList extends Expression {
    public List<Expression> es;

    private FactorList(List<Expression> es, Color color, int group) {
        super(color, group);
        this.es = es;
    }

    public static Expression of(List<Expression> es,Color color,int group) {
        return es.isEmpty()?new One(color,group):es.size()==1?es.get(0):new FactorList(es);
    }
    public static Expression of(List<Expression> es) {
        return es.isEmpty()?new One():es.size()==1?es.get(0):new FactorList(es);
    }
    public static Expression of(Expression... es) {
        return of(List.of(es));
    }

    @Override
    public String toStringHelper() {
        String str="";
        for(Expression e:es){
            str+=e.terms().size()>1?"("+e+")":e;
        }
        return str.isEmpty()?"1":str;
    }
}
