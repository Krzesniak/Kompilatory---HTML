package expression;

import app.ProgramException;

public class StringValue extends Expression {
    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        throw new ProgramException(parent.line, parent.column, String.format("Cannot cast %s to int!", value));
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
