package expression;

import app.ProgramException;

public class Subtraction extends Expression {
    private Expression left;
    private Expression right;

    public Subtraction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int intValue() {
        return left.intValue() - right.intValue();
    }

    @Override
    public String stringValue() {
        throw new ProgramException(String.format("Error at %d,%d.Cannot subtract strings.", parent.line, parent.column));
    }

    @Override
    public Expression.Type getType() {
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
