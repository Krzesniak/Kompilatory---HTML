package expression;

public class StringValue extends Expression {
    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        throw new UnsupportedOperationException(String.format("Cannot cast %s to int!", value));
    }

    @Override
    public String stringValue() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }
}
