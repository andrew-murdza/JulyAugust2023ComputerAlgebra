package expression.dexp;

public class Pi extends DoubleExp {
    public Pi(){
        super(Math.PI);
    }

    @Override
    public String toStringHelper() {
        return "\\pi";
    }
}
