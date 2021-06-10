package expression;

public class Equals extends Expression {
    private Expression left;
    private Expression right;

    public Equals(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int intValue() {
        boolean result = false;
        if (!left.getType().equals(right.getType())) return 0;
        switch (left.getType()) {
            case INTEGER -> result = (left.intValue() == right.intValue());
            case STRING -> result = (left.stringValue().equals(right.stringValue()));
        }

        if (result) return 1;
        return 0;
    }

    @Override
    public String stringValue() {
        return null;
    }

    @Override
    public Expression.Type getType() {
        return Expression.Type.INTEGER;
    }

    @Override
    public void spreadParent() {
        left.parent = parent;
        right.parent = parent;
        left.spreadParent();
        right.spreadParent();
    }
}
