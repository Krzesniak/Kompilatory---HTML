package htmlable;

import app.ProgramException;
import expression.Expression;

import java.util.List;

public class Repeat extends Htmlable {
    private final Expression arg;

    public Repeat(List<String> errors, Expression arg) {
        super(errors);
        this.arg = arg;
    }

    @Override
    public String eval() {
        try {
            StringBuilder res = new StringBuilder();

            if (arg.getType() != Expression.Type.INTEGER)
                throw new ProgramException(String.format("Error at %d, %d. Expected repeat argument to be integer.", line, column));

            for (int i = 0; i < arg.intValue(); i++) {
                for (Htmlable h : htmlables)
                    res.append(h.toString());
            }
            return res.toString();
        } catch (ProgramException pe) {
            errors.add(pe.getMessage());
            return "ERR";
        }
    }

    @Override
    public void spreadParent() {
        super.spreadParent();
        arg.parent = this;
        arg.spreadParent();
    }
}
