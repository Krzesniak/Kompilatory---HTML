package htmlable;

import expression.Expression;

import java.util.Map;

public class Switch extends Htmlable {
    private final Expression arg;
    private final Map<Expression, Htmlable> cases;
    private final Htmlable elseBlock;

    public Switch(Expression arg, Map<Expression, Htmlable> cases, Htmlable elseBlock) {
        this.arg = arg;
        this.cases = cases;
        this.elseBlock = elseBlock;
    }

    @Override
    public String toString() {
        return cases.getOrDefault(arg, elseBlock).toString();
    }

    @Override
    public void spreadParent() {
        super.spreadParent();
        arg.parent = this;
        arg.spreadParent();
    }
}
