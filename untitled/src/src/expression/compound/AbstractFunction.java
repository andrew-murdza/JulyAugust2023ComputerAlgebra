package src.expression.compound;

import expression.Expression;
import expression.dexp.Int;
import util.List;

import java.util.HashMap;


public class AbstractFunction extends Expression{
    public Expression inside;
    public String name;
    public List<HashMap<Expression,Expression>> knownVals;
    public Int n;//number of derivatives taken

    private String genPrimes(){
        if(n.i==0){
            return "";
        }
        if(n.i<4){
            String str="";
            for(int i=0;i<n.i;i++){
                str+="'";
            }
            return str;
        }
        return "^{\\left("+n+"\\right)}";
    }
    @Override
    public String toStringHelper() {
        return name+genPrimes()+"\\left("+inside+"\\right)";
    }
}
