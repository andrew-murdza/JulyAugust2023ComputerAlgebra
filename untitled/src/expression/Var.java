package expression;

import color.Color;
import lombok.AllArgsConstructor;
import set.R;
import set.Set;

public class Var extends Expression{
    public String name;
    public Set set=new R();
    public Expression val=new UndefinedExp();

    public Var(String name){
        super();
        this.name=name;
    }
    public Var(String name, Color color){
        super(color);
        this.name=name;
    }
    public Var(String name, Color color, int group){
        super(color,group);
        this.name=name;
    }
}
