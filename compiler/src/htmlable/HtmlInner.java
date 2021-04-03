package htmlable;

import expression.Expression;

public class HtmlInner extends Htmlable {
    Expression expression;

    HtmlInner(Expression expression) {
        expression.parent = this;
        this.expression = expression;
    }

    @Override
    public String toString(){
        return expression.stringValue() + "\n";
    }
}
