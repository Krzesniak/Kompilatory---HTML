package expression;

import app.ProgramException;

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
        final var leftSide = left.stringValue();
        String rightSide = null;
        try {
            rightSide = right.stringValue();
        } catch (IndexOutOfBoundsException e) {
            throw new ProgramException(e.getMessage());
        } catch (Exception e) {
            throw new ProgramException(parent.line, parent.column, "Right side of addition is not a string");
        }
        return leftSide + rightSide;
    }

    @Override
    public Liste listValue() {
        if (left.getType() != Type.LIST) {
            throw new ProgramException(parent.line, parent.column, "Left side of addition is not a list");
        }
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
