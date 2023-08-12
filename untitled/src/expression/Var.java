package expression;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import set.Set;

@AllArgsConstructor
public class Var extends Expression{
    public String name;
    public Set set;
    public Var(String name){
        this.name=name;
    }
}
