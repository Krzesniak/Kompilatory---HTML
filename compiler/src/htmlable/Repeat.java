package htmlable;

import app.ProgramException;
import expression.Expression;

public class Repeat extends Htmlable {
    private final Expression arg;

    public Repeat(Expression arg) {
        this.arg = arg;
    }

    @Override
    public String eval() {
        StringBuilder res = new StringBuilder();

        if (arg.getType() != Expression.Type.INTEGER)
            throw new ProgramException(String.format("Error at %d, %d. Expected repeat argument to be integer.", line, column));

        for (int i = 0; i < arg.intValue(); i++) {
            for (Htmlable h : htmlables)
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
