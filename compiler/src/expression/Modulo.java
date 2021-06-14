package expression;

import app.ProgramException;

public class Modulo extends Expression {
    private Expression left;
    private Expression right;

    public Modulo(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int intValue() {
        if (left.getType() != Type.INTEGER || right.getType() != Type.INTEGER)
            throw new ProgramException(String.format("Error at %d,%d. Cannot perform modulo operation on non integers", parent.line, parent.column));
        return left.intValue() % right.intValue();
    }

    @Override
    public String stringValue() {
        throw new ProgramException(parent.line, parent.column, "Cannot perform modulo operation on strings");
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public void spreadParent() {
        left.parent = parent;
        right.parent = parent;
        left.spreadParent();
        right.spreadParent();
    }
}
