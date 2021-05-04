package htmlable;

import expression.Expression;
import expression.Variable;

public class HtmlInner extends Htmlable {
    Expression expression;

    HtmlInner(Expression expression) {
        expression.parent = this;
        this.expression = expression;
    }

    @Override
    public void spreadParent(){
        expression.parent = this;
        expression.spreadParent();
    }

    @Override
    public String toString(){
        return expression.stringValue() + "\n";
    }
}
