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
        throw new RuntimeException(String.format("Cannot cast %d to string!", value));
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }
}
