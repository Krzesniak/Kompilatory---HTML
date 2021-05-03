package expression;

import htmlable.Htmlable;

public abstract class Expression {
    public enum Type {
        INTEGER,
        STRING
    }

    public Htmlable parent;

    public abstract int intValue();

    public abstract String stringValue();

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
