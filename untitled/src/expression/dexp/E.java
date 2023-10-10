package expression.dexp;

public class E extends DoubleExp{
    public E(){
        super(Math.E);
    }

    @Override
    public String toStringHelper() {
        return "e";
    }
}
