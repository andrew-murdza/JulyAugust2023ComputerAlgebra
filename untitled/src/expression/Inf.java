package expression;

public class Inf extends Expression {

    public static Expression of(boolean pos){
        return pos?new Inf():new Inf().negate();
    }

}
