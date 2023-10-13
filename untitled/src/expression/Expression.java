package expression;

import Relation.Eq;
import color.Color;
import expression.compound.*;
import expression.dexp.*;
import genBool.GenBool;
import lombok.AllArgsConstructor;
import set.Set;
import util.Helper;
import util.List;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Expression {
    //Reading Tree
    public abstract List<Expression> allNodes();//returns all subnodes
    public abstract List<FactorGroup> factorGroups();   //Factors grouped by being multiplied or divided
    public abstract List<Expression> outerMostFactors();//outer most factors
    public abstract ReplacementInfo removeAndUpdate(List<? extends Expression> es); //allows for es that don't exist in the tree
    public abstract ReplacementInfo replaceAndUpdate(List<? extends Expression> es, List<? extends Expression> fs);
    //allows for es that don't exist in the tree. make sure to update after each replacement
    public abstract ReplacementInfo replaceAndUpdate(List<? extends Expression> es, Expression e, Expression f);//
    public abstract ReplacementInfo replaceAndUpdate(FactorGroup group, Expression f);//special of previous method
    public abstract List<FactorGroup> numFactors();//factors that are considered in a numerator of the expression
    public abstract List<FactorGroup> numFactorsShallow();//factors that are considered in a numerator of the expression
    public abstract List<Expression> numFracFactors();//factors that are part of a numerator of an actual fraction
    public abstract Expression setGroupFactors(List<FactorGroup> list, int i); //set
    public abstract List<Expression> denomFactors();//all factors within denominators
    public abstract List<FactorGroup> denomFactorsShallow();//all factors within numerator of a one-level deep denominator
    public abstract List<List<FactorGroup>> numDenomFactors();//
    public abstract Expression removeMinusSignsAndUpdate(List<Expression> es);//input might be wrong

    //Will implement later
    public abstract Expression gcf(Expression e);
    public abstract Expression lcm(Expression e);
    public abstract Expression takeOut(Expression e);
    public abstract Expression simplify();


    //(I will implement later on a Class by Class basis) and then create a default method
    public abstract double toDouble();
    public abstract List<Expression> minusSignFactors();
    public abstract int numMinusFactors();
    public abstract Expression sub(Var x, Expression e);
    public abstract Expression negate();
    public abstract Expression negateWithColor(Color color);
    public abstract Expression negateWithColor(Color color, int group);//negates and colors negative sign
    public abstract Expression recip();//switcher numerator and denominator factors. If no denominator, then do 1/e

    //Steps will come later
    public abstract GenBool isPos();
    public abstract GenBool isStrPos();
    public abstract GenBool isNeg();
    public abstract GenBool isStrNeg();
    public abstract GenBool isZero();
    public abstract GenBool isNonZero();
    public abstract GenBool isFinite();


    public ReplacementInfo replaceAndUpdate(List<? extends Expression> list, BiFunction<Integer,Expression, Expression> f){
        List<Expression> list1=list.fill(p->p);
        return replaceAndUpdate(list1,list1.fillI(f));
    }
    public ReplacementInfo replaceAndUpdate(List<? extends Expression> list, Function<Expression, Expression> f){
        return replaceAndUpdate(list,(i,p)->f.apply(p));
    }
    public Expression replace(List<? extends Expression> list, BiFunction<Integer,Expression, Expression> f){
        return replaceAndUpdate(list,f).e;
    }
    public Expression replace(List<? extends Expression> list, Function<Expression, Expression> f){
        return replaceAndUpdate(list,f).e;
    }

    public Expression replace(Expression e, Expression f){
        return replaceAndUpdate(e,f).e;
    }
    public Expression replace(List<? extends Expression> es, List<? extends Expression> fs){
        return replaceAndUpdate(es,fs).e;
    }

    public ReplacementInfo replaceAndUpdate(Expression e, Expression f){
        return replaceAndUpdate(List.of(e),List.of(f));
    }

    //Editing Tree
    public Expression remove(List<? extends Expression> es){
        return removeAndUpdate(es).e;
    }

    public List<List<Term>> termGroups(){
        return nodesOfClass(TermList.class).fill(p->p.terms);
    }
    public boolean commonTerms(Expression e){
        return varPart().equals(e.varPart());
    }
    public boolean commonTerms(Expression e, Var... xs){
        return varPart(xs).equals(e.varPart(xs));
    }

    public Expression remove(Expression... es){
        return remove(List.of(es));
    }

    public Expression textBox(){
        return new TextBox(this);
    }

    public Expression setGroup(List<? extends Expression> list, int i){
        return replace(list,p->p.setGroup(i));
    }

    public Expression setGroupFactors(List<List<FactorGroup>> list){
        Expression e=copy();
        for(int i=0;i<list.size();i++){
            e=e.setGroupFactors(list.get(i),i);
        }
        return e;
    }
    public FactorGroup getCoef(){
        return new FactorGroup(outerMostFactors().filter(Expression::isConst));
    }

    public FactorGroup getCoef(List<Var> xs){
        return new FactorGroup(outerMostFactors().filter(p->p.isConst(xs)));
    }

    public FactorGroup getVarPart(){
        return new FactorGroup(outerMostFactors().filter(p->!p.isConst()));
    }

    public FactorGroup getVarPart(List<Var> xs){
        return new FactorGroup(outerMostFactors().filter(p->!p.isConst(xs)));
    }
    public Expression coef(){
        return getCoef().toExpression();
    }
    public Expression coef(List<Var> xs){
        return getCoef(xs).toExpression();
    }
    public Expression varPart(){
        return getVarPart().toExpression();
    }
    public Expression varPart(List<Var> xs){
        return getVarPart(xs).toExpression();
    }
    public Expression varPart(Var... xs){
        return varPart(List.of(xs));
    }
    public boolean isConst(Var x){
        return varExps().allTrue(p->!p.name.equals(x.name));
    }
    public boolean isConst(List<Var> xs){
        return xs.allTrue(this::isConst);
    }
    public boolean isConst(Var... xs){
        return isConst(List.of(xs));
    }
    public boolean isConst(){
        return varExps().isEmpty();
    }
    public Expression removeColor(){
        return replace(allNodes(),p->p.setColor(Color.INHERIT));
    }
    public Expression plus(List<Expression> es){
        return TermList.ofEs(List.of(this).addAll(es));
    }

    public Expression minusRaw(Expression e){
        return TermList.of(new Term(this,true),new Term(e,false));
    }
    public Expression minus(Expression e){
        return TermList.ofEs(this,e.negate());
    }
    public Expression times(List<Expression> es){
        return FactorList.of(es);
    }
    public Expression div(Expression e){
        return new Frac(this,e);
    }
    public Expression pow(Expression e){
        return new PowExp(e,this);
    }
    public Expression pow2(Expression e){
        return e.pow(this);
    }
    public Expression ln(){
        return new Ln(this);
    }

    public Expression log(Expression a){
        return Log.of(a,this);
    }
    public Expression logRaw(Expression a){
        return Log.logRaw(a,this);
    }
    public Expression sin(){
        return new Sin(this);
    }

    public Expression cos(){
        return new Cos(this);
    }

    public Expression tan(){
        return new Tan(this);
    }

    public Expression cot(){
        return new Cot(this);
    }

    public Expression sec(){
        return new Sec(this);
    }

    public Expression csc(){
        return new Csc(this);
    }

    public Expression asin(){
        return new ASin(this);
    }

    public Expression acos(){
        return new ACos(this);
    }

    public Expression atan(){
        return new ATan(this);
    }

    public Expression acot(){
        return new ACot(this);
    }

    public Expression asec(){
        return new ASec(this);
    }

    public Expression acsc(){
        return new ACsc(this);
    }

    public Expression abs(){
        return new Abs(this);
    }

    public Expression floor(){
        return new Floor(this);
    }

    public Expression p(){
        return new ParenExp(this);
    }

    public Expression root(Expression e){
        return NthRoot.of(this,e);
    }
    public Expression rootRaw(Expression e){
        return NthRoot.rawOf(this,e);
    }

    public List<Expression> filterNodes(Predicate<Expression> pred) {
        return allNodes().filter(pred);
    }
    public <T extends Expression> List<T> nodesOfClass(Class<T> clas){
        return allNodes().fillCond(clas::isInstance,p->(T)p);
    }
    public List<Term> terms() {
        return nodesOfClass(Term.class);
    }

    public List<NthRoot> roots() {
        return nodesOfClass(NthRoot.class);
    }


    public List<Expression> factors() {
        return Helper.flatten1Level(nodesOfClass(FactorList.class).fill(p->p.es).addAll(nodesOfClass(Frac.class).
                fill(p->List.of(p.e,p.f))));
    }

    public List<PowExp> powexps(){
        return nodesOfClass(PowExp.class);
    }


    public List<Expression> pows(){
        return powexps().fill(p->p.f);
    }

    public List<TermList> termLists(){
        return nodesOfClass(TermList.class);
    }

    public List<Sqrt> sqrts(){
        return nodesOfClass(Sqrt.class);
    }

    public List<NthRoot> nthRoots(){
        return nodesOfClass(NthRoot.class).filter(p->!(p instanceof Sqrt));
    }

    public List<Frac> fracs(){
        return nodesOfClass(Frac.class);
    }

    public List<Var> varExps(){
        return nodesOfClass(Var.class);
    }

    public Expression sub(List<Var> xs, List<Expression> es) {
        Expression e = this;
        for (int i = 0; i < xs.size(); i++) {
            e = e.sub(xs.get(i), es.get(i));
        }
        return e;
    }

    public Expression setGroupFactors(int i, FactorGroup... list) {
        return setGroupFactors(List.of(list), i);
    }

    public Expression setGroupSign(List<Term> list, int i) {
        return replace(list, p -> ((Term) p).setSignGroup(i));
    }

    public Expression root(double d) {
        return root(DoubleExp.of(d));
    }

    public Expression log(double d) {
        return log(DoubleExp.of(d));
    }

    public Expression plus(double d) {
        return plus(DoubleExp.of(d));
    }

    public Expression minus(double d) {
        return minus(DoubleExp.of(d));
    }

    public Expression times(double d) {
        return times(DoubleExp.of(d));
    }

    public Expression div(double d) {
        return div(DoubleExp.of(d));
    }

    public Expression pow(double d) {
        return pow(DoubleExp.of(d));
    }

    public Expression copy(){
        return Helper.copy(this);
    }

    public Expression setGroup(int i) {
        Expression copy=copy();
        copy.group=i;
        return copy;
    }

    ; //set

    public Set solveZero(Var x) {
        return new Eq(this, new Zero()).solve(x);
    }

    public boolean isOne() {
        return equals(new One());
    }

    public boolean isNegOne() {
        return equals(new NegOne());
    }

    public Expression sqr() {
        return pow(2);
    }

    public Expression sqrt() {
        return root(2);
    }

    public Expression cbrt() {
        return root(3);
    }

    public Expression plus(Expression... es) {
        return plus(List.of(es));
    }

    public Expression times(Expression... es) {
        return times(List.of(es));
    }

    public Expression replaceWithSingle(List<? extends Expression> list, Expression e) {
        if (list.isEmpty()) {
            return this;
        }
        return replace(list.get(0), e).remove(list.subList(1));
    }

    //Doesn't create new instance
    public Expression setColor(Color color) {
        this.color = color;
        return this;
    }

    public Expression setColor(int i) {
        int n = Color.values().length;
        color = Color.values()[i % (n - 2)];
        return this;
    }

    public String toStringDoubleDollar() {
        return "$$" + this + "$$";
    }

    public String toStringSingleDollar() {
        return "$" + this + "$";
    }

    public String toStringSingleDollarBigText() {
        return "$\\bigtext{" + this + "}$";
    }

    public List<Var> vars() {
        return varExps().removeRepeats();
    }

    public abstract String toStringHelper();

    //makes subscripts and superscripts look nicer
    public static String cleanUpScript(String string) {
        return string.replaceAll("\\\\frac", "\\powfrac");
    }

    @Override
    public String toString() {
        String str = toStringHelper();
        return color != Color.INHERIT ? "\\" + color + "{" + str + "}" : str;
    }

    public Color color = Color.INHERIT;
    public int group = -1;

    public Expression(Color color) {
        this.color = color;
    }
    public Expression(Color color, int group) {
        this.color = color;
        this.group=group;
    }

    public static String applyColor(Color color, String string) {
        return string.isEmpty() ? "" : color != Color.INHERIT ? "\\" + color + "{" + string + "}" : string;
    }
    public Expression() {

    }
    @AllArgsConstructor
    public static class ReplacementInfo{
        Expression e;//result from replacement
        Function<Expression,Expression> f;//updates function indices
    }
}
