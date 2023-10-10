package actions.simplification;

import actions.StepsResult;

public interface StepGen<T,U> {
    public StepsResult<U> createSteps(T t);
}
