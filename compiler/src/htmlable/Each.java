package htmlable;

import expression.Expression;
import expression.Variable;

import java.util.List;

public class Each extends Htmlable {
    private final List<Expression> args;
    private final Variable var;

    public Each(Variable var, List<Expression> args) {
        this.var = var;
        this.args = args;
        addVariable(var);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Expression arg : args) {
            switch (arg.getType()) {
                case STRING -> var.setValue(arg.stringValue());
                case INTEGER -> var.setValue(arg.intValue());
                default -> {
                    //TODO no jakisikej łapani błyndów
                }
            }

            for (Htmlable h : htmlables)
                res.append(h.toString());
        }
        return res.toString();
    }

    @Override
    public void spreadParent() {
        super.spreadParent();
        for(Expression arg : args) {
            arg.parent = this;
            arg.spreadParent();
        }
    }
}
