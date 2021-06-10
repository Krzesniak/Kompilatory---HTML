package expression;

import app.ProgramException;

public class VariableReference extends Expression {
    private final String id;

    public VariableReference(String id) {
        this.id = id;
    }

    private Expression getVariable() {
        final var variable = parent.getVariable(id);
        if (variable == null) {
            throw new ProgramException(String.format("Error at %d,%d.Variable %s does not exist", parent.line, parent.column, id));
        }
        return variable;
    }

    @Override
    public int intValue() {
        return getVariable().intValue();
    }

    @Override
    public String stringValue() {
        return getVariable().stringValue();
    }

    @Override
    public Liste listValue() {
        return getVariable().listValue();
    }

    @Override
    public Type getType() {
        return getVariable().getType();
    }
}
