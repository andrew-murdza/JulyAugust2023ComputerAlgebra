package expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class FactorGroup {
    public List<Expression> factors;//Might be ExpressionNode
    public abstract List<Expression> numFactors();
    public abstract List<Expression> denomFactors();
    public abstract List<Expression> topFactors();//outermost numerator factors
    public abstract List<Expression> botFactors();//outermost denominator factors

    public abstract Expression toExpression();
    public abstract FactorGroup negate();
}
