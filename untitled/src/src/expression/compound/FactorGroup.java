package src.expression.compound;

import expression.Expression;
import lombok.AllArgsConstructor;
import util.List;
@AllArgsConstructor
public class FactorGroup {
    public List<Expression> factors;//Might be ExpressionNode
    public abstract List<Expression> numFactors();
    public abstract List<Expression> denomFactors();
    public abstract List<Expression> topFactors();//outermost numerator factors
    public abstract List<Expression> botFactors();//outermost denominator factors

    public abstract Expression toExpression();
    public abstract FactorGroup negate();

    public int numMinusSigns() {
        return toExpression().numMinusSigns();
    }
    public String toStringDoubleDollar(){
        return toExpression().toStringDoubleDollar();
    }
    public String toStringSingleDollar() {
        return toExpression().toStringSingleDollar();
    }
    public String toStringSingleDollarBigText() {
        return toExpression().toStringSingleDollarBigText();
    }

    public Expression removeMinusSigns() {
        return toExpression().removeMinusSigns();
    }
}
