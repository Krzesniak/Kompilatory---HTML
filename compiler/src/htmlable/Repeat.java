package htmlable;

import expression.Expression;

public class Repeat extends Htmlable{
    private Expression arg;
    public Repeat(Expression arg) {
        this.arg = arg;
    }
    @Override
    public String toString() {
        String res = "";
        for(int i = 0; i < arg.intValue(); i++) {
            for(Htmlable h : htmlables)
                res += h.toString();
        }
        return res;
    }

    @Override
    public void spreadParent() {
        super.spreadParent();
        arg.parent = this;
        arg.spreadParent();
    }
}
