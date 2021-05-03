package expression;

public class VariableReference extends Expression {
    private final String id;

    public VariableReference(String id) {
        this.id = id;
    }

    private Expression getVariable()  {
        return parent.getVariable(id);
    }

    @Override
    public int intValue()  {
        return getVariable().intValue();
    }

    @Override
    public String stringValue() {
        return getVariable().stringValue();
    }

    @Override
    public Type getType() {
        return getVariable().getType();
    }
}
