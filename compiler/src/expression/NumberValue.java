package expression;

public class NumberValue extends Expression {
    private int value;

    public NumberValue(int value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public String stringValue() {
        throw new UnsupportedOperationException("Cannot cast int to string!");
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }
}
