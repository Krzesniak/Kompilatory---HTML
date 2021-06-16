package htmlable;

import app.ProgramException;
import expression.Expression;
import expression.Variable;

import java.util.List;

public class Each extends Htmlable {
    private final List<Expression> args;
    private final Variable var;

    public Each(List<String> errors, Variable var, List<Expression> args) {
        super(errors);
        this.var = var;
        this.args = args;
        addVariable(var);
    }

    @Override
    public String eval() {
        try {
            StringBuilder res = new StringBuilder();
            for (Expression arg : args) {
                switch (arg.getType()) {
                    case STRING -> var.setValue(arg.stringValue());
                    case INTEGER -> var.setValue(arg.intValue());
                }

                for (Htmlable h : htmlables)
                    res.append(h.toString());
            }
            return res.toString();
        } catch (ProgramException pe) {
            errors.add(pe.getMessage());
            return "ERROR";
        }
    }

    @Override
    public void spreadParent() {
        super.spreadParent();
        for (Expression arg : args) {
            arg.parent = this;
            arg.spreadParent();
        }
    }
}
