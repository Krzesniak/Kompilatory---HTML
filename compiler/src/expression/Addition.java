package expression;

import java.util.ArrayList;
import java.util.List;

public class Addition extends Expression {
    private Expression left;
    private Expression right;

    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int intValue() {
        return left.intValue() + right.intValue();
    }

    @Override
    public String stringValue() {
        return switch (left.getType()) {
            case INTEGER -> Integer.toString(left.intValue() + right.intValue());
            case LIST -> listValue().stringValue();
            case STRING -> left.stringValue() + right.stringValue();
        };
    }

    @Override
    public Liste listValue() {
        ArrayList<Expression> content = new ArrayList<>(left.listValue().getContent());
        if (right.getType() != Type.LIST) {
            content.add(right);
        } else {
            content.addAll(right.listValue().getContent());
        }
        return new Liste(content);
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
