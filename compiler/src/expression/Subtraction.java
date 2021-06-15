package expression;

import app.ProgramException;

import static java.util.stream.Collectors.toList;

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
        throw new ProgramException(parent.line, parent.column, "Cannot cast subtraction result to string. Maybe use STRING function?");
    }

    @Override
    public Liste listValue() {
        if(right.getType()!=Type.LIST){
            var list = left.listValue().getContent();
            var newList = list.stream()
                    .filter(it -> !it.equals(right))
                    .collect(toList());
            return new Liste(newList);
        }
        throw new ProgramException(parent.line, parent.column, "Cannot subtract lists");
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
