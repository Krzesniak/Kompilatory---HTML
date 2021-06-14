package expression;

import app.ProgramException;

public class LessThan extends Expression {
    private Expression left;
    private Expression right;

    public LessThan(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int intValue() {
        return Boolean.compare(left.intValue() < right.intValue(), false);
    }

    @Override
    public String stringValue() {
        throw new ProgramException(parent.line, parent.column, "Cannot compare strings");
    }

    @Override
    public Type getType() {
        return left.getType();
    }

    @Override
    public void spreadParent() {
        left.parent = parent;
        right.parent = parent;
        left.spreadParent();
        right.spreadParent();
    }
}
