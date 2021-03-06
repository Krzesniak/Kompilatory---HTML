package htmlable;

import app.ProgramException;
import expression.Expression;

import java.util.List;
import java.util.Map;

public class Switch extends Htmlable {
    private final Expression arg;
    private final Map<Expression, Htmlable> cases;
    private final Htmlable elseBlock;

    public Switch(List<String> errors,Expression arg, Map<Expression, Htmlable> cases, Htmlable elseBlock, int line, int column) {
        super(errors);
        this.line = line;
        this.column = column;

        this.arg = arg;
        this.cases = cases;
        this.elseBlock = elseBlock;
    }

    @Override
    public String eval() {
        try {
        cases.keySet().stream()
                .filter(it -> !it.getType().equals(arg.getType()))
                .forEach(it -> {
                    throw new ProgramException(String.format("Error at: %s, %s. Switch case %s is not the same type as the argument %s", line, column, it.toString(), arg.toString()));
                });
        return cases.getOrDefault(arg, elseBlock).toString();
        }
        catch (ProgramException pe) {
            errors.add(pe.getMessage());
            return "err";
        }
    }

    @Override
    public void spreadParent() {
        super.spreadParent();
        arg.parent = this;
        arg.spreadParent();

        for (var expr : cases.keySet()) {
            expr.parent = this;
            expr.spreadParent();
        }
        for (Htmlable h : cases.values()) {
            h.parent = this;
            h.spreadParent();
        }

        elseBlock.parent = this;
        elseBlock.spreadParent();
    }
}
