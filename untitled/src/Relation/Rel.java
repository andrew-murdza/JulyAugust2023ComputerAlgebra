package Relation;

import color.Color;
import expression.Expression;
import expression.Var;
import lombok.AllArgsConstructor;
import set.Set;

import util.List;

import java.security.spec.ECField;

@AllArgsConstructor
public class Rel {
    public Expression left;
    public Expression right;
    public List<Break> breaks;

    public Rel(Expression e, List<Break> breaks){
        this(e,breaks.last().e,breaks);
    }
    public String toStringSingleDollarBigText() {
        return "$\\bigtext{"+this+"}$";
    }
    public String toStringDoubleDollar(){
        return "$$"+this+"$$";
    }

    public enum Sign{
        GR,STRGR,LE,STRLE,NE,EQ,NONE;
        public static Sign getSign(Sign sign1,Sign sign2){
            if(sign1==Sign.EQ){
                return sign2;
            }
            if(sign2==Sign.EQ){
                return sign1;
            }
            if(sign1==sign2){
                return sign1==Sign.NONE||sign1==Sign.NE?Sign.NONE:sign1;
            }
            if((sign1==Sign.GR||sign1==Sign.STRGR)&&(sign2==Sign.GR||sign2==Sign.STRGR)){
                return Sign.STRGR;//works because can't both be GR due to earlier equals check
            }
            if((sign1==Sign.LE||sign1==Sign.STRLE)&&(sign2==Sign.LE||sign2==Sign.STRLE)){
                return Sign.STRLE;//works because can't both be GR due to earlier equals check
            }
            return Sign.NONE;
        }
        public static Sign reverse(Sign sign){
            if(sign==LE){
                return GR;
            }
            if(sign==GR){
                return LE;
            }
            if(sign==STRGR){
                return STRLE;
            }
            if(sign==STRLE){
                return STRGR;
            }
            return sign;
        }
        public static Sign complement(Sign sign){
            if(sign==NE){
                return EQ;
            }
            if(sign==EQ){
                return NE;
            }
            if(sign==NONE){
                return NONE;
            }
            if(sign==LE){
                return STRGR;
            }
            if(sign==GR){
                return STRLE;
            }
            if(sign==STRGR){
                return LE;
            }
            if(sign==STRLE){
                return GR;
            }
            return null;//Shouldn't run
        }
    }

    public Rel(Expression start, Break... breaks){
        this(start, List.of(breaks));
    }

    public Rel addStep(Color color,Sign sign, Expression e, boolean newLine){
        return new Rel(left,breaks.add(new Break(color,sign,e,newLine)));
    }
    public Rel addStep(Sign sign, Expression e){
        return addStep(Color.INHERIT,sign,e,true);
    }
    public Rel addStep(Expression e){
        return addStep(Sign.EQ,e);
    }
    public Sign getSign(){
        if(breaks.size()==0){
            return Sign.NONE;
        }
        Sign sign=breaks.get(0).sign;
        for(int i=1;i<breaks.size();i++){
            sign=Sign.getSign(sign,breaks.get(i).sign);
        }
        return sign;
    }
    public abstract Set solve(Var x);
    @AllArgsConstructor
    public static class Break{
        public Color color;
        public Sign sign;
        public Expression e;
        public boolean newLine;
        public Break(Sign sign, Expression e, boolean newLine){
            this(Color.INHERIT,sign,e,newLine);
        }
    }
}
