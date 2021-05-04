package expression;

public class Modulo extends Expression {
    private Expression left;
    private Expression right;

    public Modulo(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int intValue() {
        return left.intValue() % right.intValue();
    }

    @Override
    public String stringValue() {
        return null;
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
