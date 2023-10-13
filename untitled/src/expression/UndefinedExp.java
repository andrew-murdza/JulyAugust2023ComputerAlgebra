package expression;

import color.Color;

public class UndefinedExp extends Expression {
    public UndefinedExp(Color color){
        super(color);
    }
    public UndefinedExp(){
        super();
    }
    public UndefinedExp(Color color, int group){
        super(color,group);
    }
}
