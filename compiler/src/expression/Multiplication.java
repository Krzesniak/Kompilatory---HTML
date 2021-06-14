package expression;

import app.ProgramException;

public class Multiplication extends Expression {
    private Expression left;
    private Expression right;

    public Multiplication(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int intValue() {
        return left.intValue() * right.intValue();
    }

    @Override
    public String stringValue() {
        throw new ProgramException(parent.line, parent.column, "Cannot multiply strings");
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
