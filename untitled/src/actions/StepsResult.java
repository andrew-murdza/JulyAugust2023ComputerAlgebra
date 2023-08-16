package actions;

import actions.structure.Step;
import lombok.AllArgsConstructor;

import util.List;

@AllArgsConstructor
public class StepsResult<T> {
    public List<Step> steps;
    public T result;
    public StepsResult(T result,Step... steps){
        this(List.of(steps),result);
    }
}
