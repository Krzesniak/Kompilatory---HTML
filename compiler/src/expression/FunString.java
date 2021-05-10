package expression;

public class FunString extends Expression {
    private final Expression arg;

    public FunString(Expression arg) {
        this.arg = arg;
    }

    @Override
    public int intValue() {
        throw new RuntimeException();
    }

    @Override
    public String stringValue() {
        return Integer.toString(arg.intValue());
    }

    @Override
    public Expression.Type getType() {
        return Type.STRING;
    }

    @Override
    public void spreadParent() {
        arg.parent = parent;
        arg.spreadParent();
    }
}