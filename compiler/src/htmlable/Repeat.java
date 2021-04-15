package htmlable;

import expression.Expression;

public class Repeat extends Htmlable{
    private final Expression arg;
    public Repeat(Expression arg) {
        this.arg = arg;
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < arg.intValue(); i++) {
            for(Htmlable h : htmlables)
                res.append(h.toString());
        }
        return res.toString();
    }

    @Override
    public void spreadParent() {
        super.spreadParent();
        arg.parent = this;
        arg.spreadParent();
    }
}
