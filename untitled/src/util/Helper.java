package util;

import expression.Expression;
import expression.Var;
import expression.compound.FactorGroup;
import expression.compound.Term;
import expression.dexp.Zero;
import genBool.GenBool;
import lombok.NonNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Helper {
    public static <T> List<T> remove(List<T> list, int i){
        List<T> returnList=new ArrayList<>(list);
        returnList.remove(i);
        return returnList;
    }
    public static List<Integer> range(int i){
        List<Integer> returnList=new ArrayList<>();
        for(int j=0;j<=i;i++){
            returnList.add(i);
        }
        return returnList;
    }
    public static <T> List<T> removeAll(List<T> list, List<T> list1){
        List<T> returnList=new ArrayList<>(list);
        returnList.removeAll(list1);
        return returnList;
    }
    @SafeVarargs
    public static <T> List<T> asList(T... ts){
        return new ArrayList<>(Arrays.asList(ts));
    }
    @SafeVarargs
    public static <T> List<T> addAll(List<T> list, T... ts){
        return addAll(list,asList(ts));
    }

    public static <T> List<T> addAll(List<T> list, List<T> ts){
        List<T> returnList=new ArrayList<>(list);
        returnList.addAll(ts);
        return returnList;
    }
    public static <T> List<T> filter(List<T> list, Predicate<T> pred) {
        return list.stream().filter(pred).toList();
    }

    public static <T> List<List<T>> filterDeep(List<List<T>> list, Predicate<T> pred) {
        return Helper.fill(list,p->Helper.filter(p,pred));
    }

    public static <T> List<T> flatten1Level(List<List<T>> list){
        List<T> returnList=new ArrayList<>();
        for(List<T> list1:list){
            returnList.addAll(list1);
        }
        return returnList;
    }

    public static <T> List<T> filterGen(List<T> list, Function<T,GenBool> f) {
        return list.stream().filter(p->f.apply(p).isTrue()).toList();
    }

    public static <T> List<T> fromIndex(List<T> list, int i){
        return list.subList(i,list.size());
    }

    public static String colorText(String text, int type, String color, String command){
        if(type==0){
            return "<span class="+color+">"+text+"</span>";
        }
        if(type==3){
            return "\\"+command+"{"+text+'}';

        }
        return "";
    }
    public static <T,U> List<U> fillI(List<T> inputs, BiFunction<T,Integer,U> f) {
        List<U> returnList=new ArrayList<>();
        for(int i=0;i<inputs.size();i++){
            returnList.add(f.apply(inputs.get(i),i));
        }
        return returnList;
    }
    public static <T,U> List<U> fill(List<T> inputs, Function<T,U> f) {
        return fillI(inputs,(p,i)->f.apply(p));
    }

    public static <T> List<T> duplicate(int i, T t){
        return fill(range(i),p->t);
    }

    public static <T,U> List<U> fillCond(List<T> inputs, Predicate<T> pred, Function<T,U> f) {
        return fill(inputs.stream().filter(pred).toList(),f);
    }

    public static String blueText(String text, int type){
        return colorText(text,type,"blue","b");
    }
    public static String blueText(String text){
        return blueText(text,0);
    }

    public static String goldText(String text, int type) {
        return colorText(text,type,"gold","go");
    }

    public static String goldText(String text) {
        return goldText(text,0);
    }
    public static String greenText(String text, int type) {
        return colorText(text,type,"green","g");
    }
    public static String greenText(String text){
        return greenText(text,0);
    }

    public static String orangeText(String text, int type) {
        return colorText(text,type,"orange","o");
    }
    public static String orangeText(String text){
        return orangeText(text,0);
    }


    public static <T> int countMatches(List<T> list, Predicate<T> f){
        return (int)list.stream().filter(f).count();
    }

    public static <T> boolean allTrue(List<T> list,Predicate<T> f){
        return countMatches(list,f)==list.size();
    }
    public static <T> boolean anyTrue(List<T> list,Predicate<T> f){
        return countMatches(list,f)>0;
    }
    public static <T> boolean allTrueGen(List<T> list,Function<T, GenBool> f){
        return allTrue(list,p->f.apply(p).isTrue());
    }
    public static <T> boolean anyTrueGen(List<T> list,Function<T,GenBool> f){
        return anyTrue(list,p->f.apply(p).isTrue());
    }

    public static String textBoxArray(List<String> prompts,List<String> responses, List<Boolean> eqSign){
        String returnString="\\[\\a{";
        for(int i=0;i<prompts.size();i++){
            String[] strs=new String[]{"\\t{",":}","\\tb"};
            if(eqSign.get(i)){
                strs=new String[]{"","","=\\tbs"};
            }
            returnString+=(i>0?"\\\\":"")+strs[0]+prompts.get(i)+strs[1]+"&"+strs[2]+"{"+responses.get(i)+"}";
        }
        return returnString+"}\\]";
    }
    public static String textBoxArray(List<String> prompts,List<Expression> responses, List<Boolean> eqSign){
        return textBoxArray(prompts,Helper.fill(responses,Expression::toString),eqSign);
    }
    public static String textBoxArray(String prompt,String response, boolean eqSign){
        return textBoxArray(Helper.asList(prompt),Helper.asList(response),Helper.asList(eqSign));
    }

    public static String nthWordCapital(int i) {
        //TODO;
        return "";
    }
    public static String nthWordNoCapital(int i) {
        //TODO;
        return "";
    }

    public static Expression sum(List<Expression> es){
        Expression e=new Zero();
        for(Expression e1:es){
            e=e.plus(e1).simplify();
        }
        return e;
    }

    public static String listExp(List<? extends Expression> list) {
        String str="";
        for(int i=0;i<list.size();i++){
            str+=(i>0?", \\":"")+list.get(i).toString();
        }
        return str;
    }

    public static <T> void forEachI(List<T> list, BiConsumer<Integer,T> f){
        for(int i=0;i<list.size();i++){
            f.accept(i,list.get(i));
        }
    }

    public static <T,U> List<List<U>> fillDeepI(List<List<T>> inputs, BiFunction<T,Integer,U> f) {
        return fill(inputs,p->fillI(p,f));
    }

    public static <T> List<T> add(List<T> list, T... ts) {
        list.addAll(Helper.asList(ts));
        return list;
    }


    public static @NonNull List<FactorGroup> filterOutInnerFactorGroup(List<FactorGroup> groups) {
        //TODO
        return new ArrayList<>();
    }
    public static @NonNull List<Expression> filterOutInner(List<? extends Expression> groups) {
        //TODO
        return new ArrayList<>();
    }

    public static List<Var> vars(List<String>strs) {
        return Helper.fill(strs, Var::new);
    }
    public static List<Var> vars(String... strs){
        return vars(Helper.asList(strs));
    }
}
