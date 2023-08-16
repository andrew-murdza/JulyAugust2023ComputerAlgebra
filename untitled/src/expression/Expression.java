package expression;

import Relation.Eq;
import color.Color;
import expression.compound.*;
import expression.dexp.NegOne;
import expression.dexp.One;
import expression.dexp.Zero;
import genBool.GenBool;
import set.Set;
import util.List;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class Expression {

    public Color color=Color.INHERIT;
    public int group=-1;
    public Expression(Color color){
        this.color=color;
    }
    public Expression(){

    }

    //Related to Tree, all have color versions
    public abstract void assignGroup(int group);
    public abstract Expression plus(List<Expression> es);
    public Expression plus(Expression... es){
        return plus(List.of(es));
    }
    public Expression times(Expression... es){
        return times(List.of(es));
    }
    public abstract Expression minus(List<Expression> es);
    public abstract Expression times(List<Expression> es);
    public abstract Expression div(List<Expression> es);
    public Expression div(Expression e){
        return div(List.of(e));
    }
    public abstract Expression pow(Expression e);
    public abstract Expression plus(double d);
    public abstract Expression minus(double d);
    public abstract Expression times(double d);
    public abstract Expression div(double d);
    public abstract Expression pow(double d);
    public abstract Expression ln();
    public abstract Expression log(Expression a);
    public abstract Expression log(double d);
    public abstract Expression sin();
    public abstract Expression cos();
    public abstract Expression tan();
    public abstract Expression cot();
    public abstract Expression sec();
    public abstract Expression csc();
    public abstract Expression asin();
    public abstract Expression acos();
    public abstract Expression atan();
    public abstract Expression acot();
    public abstract Expression asec();
    public abstract Expression acsc();
    public abstract Expression abs();
    public abstract Expression floor();
    public abstract Expression p();//Wraps expression in parentheses
    public abstract Expression root(Expression e);
    public abstract Expression root(double d);
    public abstract Expression recip();


    //Related to Tree
    public abstract List<Term> terms();                 //All term nodes in the expression (recursive)
    public abstract List<List<Term>> termGroups();      //terms but grouped by parent node
    public abstract Expression remove(List<? extends Expression> es);    //removes expression node
    public abstract Expression remove(Expression... es);    //removes expression node
    public abstract Expression replace(Expression e, Expression f);   //replaces expression node
    public abstract Expression replace(List<? extends Expression> list, Function<Expression,Expression> f);   //replaces expression node
    public abstract <T>Expression replaceForEach(List<? extends T> list,Function<List<? extends T>,Expression> f);
    public abstract <T>Expression replaceForEachI(List<? extends T> list, BiFunction<Integer,List<? extends T>,Expression> f);
    public abstract List<Expression> factors();         //All factors in the expression (recursive)
    public abstract List<FactorGroup> factorGroups();   //Factors grouped by being multiplied or divided w/0 being
                                                        //raised to powers
    public abstract List<PowExp> powexps();            //all power expressions
    public abstract List<Expression> roots();           //all roots
    public abstract List<Expression> pows();//all powers
    public abstract int numMinusFactors();
    public abstract Expression setGroup(List<? extends Expression> list, int i); //set
    public abstract Expression setGroupFactors(List<FactorGroup> list, int i); //set
    public Expression setGroupFactors(int i,FactorGroup... list){
        return setGroupFactors(List.of(list),i);
    }
    public abstract List<Expression> denomFactors();//all factors within denominators
    public abstract List<FactorGroup> denomFactorsShallow();//all factors within numerator of a one-level deep denominator
    public abstract List<TermList> termLists();//TermLists
    public abstract List<FactorGroup[]> numDenomFactors();//shallow numerators and denominators
    public abstract List<Sqrt> sqrts();
    public abstract List<NthRoot> nthRoots();//Doesn't include sqrts
    public abstract List<Frac> fracs();//fracs
    public abstract Expression removeColor();//sets all colors to INHERIT
    public abstract Expression textBox();//Wraps inside of textbox
    public Expression setGroupSign(List<Term> list, int i){
        return replace(list,p->((Term)p).setSignGroup(i));
    }

    public abstract List<Var> varExps();
    //Unrelated to Tree
    public abstract double toDouble();
    public abstract boolean isConst(Var x);
    public abstract boolean isConst();
    public abstract GenBool isPos();
    public abstract GenBool isStrPos();
    public abstract GenBool isNeg();
    public abstract GenBool isStrNeg();
    public abstract GenBool isZero();
    public abstract GenBool isNonZero();
    public abstract Expression sub(Var x, Expression e);
    public Expression sub(List<Var> xs, List<Expression> es){
        Expression e=this;
        for(int i=0;i<xs.size();i++){
            e=e.sub(xs.get(i),es.get(i));
        }
        return e;
    }
    public abstract List<Expression> getCoef();//Might need to be ExpressionNode or something like that
    public abstract List<Expression> getCoef(List<Var> xs);//Might need to be ExpressionNode or something like that
    public abstract Expression getVarPart();//Part that is not the coefficient without considering position
    public abstract Expression getVarPart(List<Var> xs);//Part that is not the coefficient without considering position
    public abstract Expression coef();
    public abstract Expression coef(List<Var> xs);
    public abstract Expression gcf(Expression e);
    public abstract Expression lcm(Expression e);
    public abstract Expression takeOut(Expression e);
    public abstract Expression simplify();
    public abstract List<FactorGroup> numFactors();//factors that are considered in a numerator of the expression

    public abstract boolean commonTerms(Expression e);//Determines if the two expressions are common terms
    public abstract Expression negate();
    public abstract GenBool isFinite();

    public Set solveZero(Var x){
        return new Eq(this,new Zero()).solve(x);
    }
    public boolean isOne(){
        return equals(new One());
    }
    public boolean isNegOne(){
        return equals(new NegOne());
    }
    public Expression sqr(){
        return pow(2);
    }
    public Expression sqrt(){
        return root(2);
    }
    public Expression cbrt(){
        return root(3);
    }

    public Expression replaceWithSingle(List<? extends Expression> list, Expression e){
        if(list.isEmpty()){
            return this;
        }
        return replace(list.get(0),e).remove(list.subList(1));
    }
    //Doesn't create new instance
    public Expression setColor(Color color){
        this.color=color;
        return this;
    }
    public Expression setColor(int i){
        int n=Color.values().length;
        color=Color.values()[i%(n-2)];
        return this;
    }

    public String toStringDoubleDollar(){
        return "$$"+ this +"$$";
    }

    public String toStringSingleDollar() {
        return "$"+ this +"$";
    }
    public String toStringSingleDollarBigText() {
        return "$\\bigtext{"+ this +"}$";
    }

    public List<Var> vars() {
        return varExps().removeRepeats();
    }
}
