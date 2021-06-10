package expression;

import app.ProgramException;

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
        throw new ProgramException(String.format("Error at %d,%d.Cannot cast %d to string!", parent.line, parent.column, value));
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }
}
