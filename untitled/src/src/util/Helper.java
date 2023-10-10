package src.util;

import expression.Expression;
import expression.Var;
import expression.compound.FactorGroup;
import expression.dexp.Zero;
import lombok.NonNull;
import util.List;

public class Helper {

    public static <T> util.List<T> flatten1Level(util.List<util.List<T>> list){
        util.List<T> returnList= new util.List<>();
        for(util.List<T> list1:list){
            returnList.addAll(list1);
        }
        return returnList;
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
    public static String textBoxArrayStr(util.List<String> prompts, util.List<String> responses, util.List<Boolean> eqSign){
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
    public static String textBoxArray(util.List<String> prompts, util.List<Expression> responses, util.List<Boolean> eqSign){
        return textBoxArrayStr(prompts,responses.fill(Expression::toString),eqSign);
    }
    public static String textBoxArray(String prompt,String response, boolean eqSign){
        return textBoxArrayStr(util.List.of(prompt), util.List.of(response), util.List.of(eqSign));
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

    public static String nthWordCapital(int i) {
        //TODO;
        return "";
    }
    public static String nthWordNotCapital(int i) {
        //TODO;
        return "";
    }

    public static Expression sum(util.List<Expression> es){
        Expression e=new Zero();
        for(Expression e1:es){
            e=e.plus(e1).simplify();
        }
        return e;
    }

    public static String listExp(util.List<? extends Expression> list) {
        String str="";
        for(int i=0;i<list.size();i++){
            str+=(i>0?", \\":"")+list.get(i).toString();
        }
        return str;
    }

    public static @NonNull util.List<FactorGroup> filterOutInnerFactorGroup(util.List<FactorGroup> groups) {
        //TODO
        return new util.List<>();
    }
    public static @NonNull util.List<Expression> filterOutInner(util.List<? extends Expression> groups) {
        //TODO
        return new util.List<>();
    }

    public static util.List<Var> vars(util.List<String> strs) {
        return strs.fill(Var::new);
    }
    public static util.List<Var> vars(String... strs){
        return vars(List.of(strs));
    }
}
