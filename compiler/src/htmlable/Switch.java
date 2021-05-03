package htmlable;

import expression.Expression;

public class Switch extends Htmlable {
    private final Expression arg;

    public Switch(Expression arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        return htmlables.stream()
                .map(Htmlable::toString)
                .reduce("", String::concat);
    }

    @Override
    public void spreadParent() {
        super.spreadParent();
        arg.parent = this;
        arg.spreadParent();
    }
}
