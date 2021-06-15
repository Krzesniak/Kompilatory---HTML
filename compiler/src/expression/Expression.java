package expression;

import app.ProgramException;
import htmlable.Htmlable;

public abstract class Expression {
    public enum Type {
        INTEGER,
        STRING,
        LIST
    }

    public Htmlable parent;

    public int intValue() {
        throw new UnsupportedOperationException();
    }

    public String stringValue() {
        throw new UnsupportedOperationException();
    }

    public Liste listValue() {
        throw new ProgramException(parent.line,parent.column, "Variable is not a list");
    }

    public abstract Type getType();

    public void spreadParent() {
    }

    @Override
    public int hashCode() {
        try {
            return intValue();
        }
        catch(Exception e) {
        }
        try {
            return stringValue().hashCode();
        }
        catch(Exception e) {
        }
        try {
            return listValue().toString().hashCode();
        }
        catch(Exception e) {
        }
        return 123456;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Expression)) {
            return false;
        }
        final Expression other = (Expression) that;
        return getType() == Type.INTEGER ? this.intValue() == other.intValue() : this.stringValue().equals(other.stringValue());
    }

    @Override
    public String toString() {
        try {
            return stringValue();
        } catch(Exception e) {
            return String.valueOf(intValue());
        }
    }
}
