package expression;

import htmlable.Htmlable;

public abstract class Expression {
    public enum Type {
        INTEGER,
        STRING,
        LIST
    }

    public Htmlable parent;

    public int intValue() {
        throw new RuntimeException();
    }

    public String stringValue() {
        throw new RuntimeException();
    }

    public Liste listValue() {
        throw new RuntimeException();
    }

    public abstract Type getType();

    //probably evil
    public void spreadParent() {
    }

    @Override
    public int hashCode() {
        return getType() == Type.INTEGER ? intValue() : stringValue().hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Expression)) {
            return false;
        }
        final Expression other = (Expression) that;
        return getType() == Type.INTEGER ? this.intValue() == other.intValue() : this.stringValue().equals(other.stringValue());
    }
}
