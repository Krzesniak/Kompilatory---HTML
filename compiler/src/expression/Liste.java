package expression;

import java.util.List;

public class Liste extends Expression{
    private final List<Expression> content;

    Liste(List<Expression> content) {
        this.content = content;
    }

    @Override
    public Liste listValue() {
        return this;
    }

    @Override
    public Type getType() {
        return Type.LIST;
    }

    public List<Expression> getContent() {
        return content;
    }

    @Override public void spreadParent(){
        for(Expression e : content) {
            e.parent = parent;
            e.spreadParent();
        }
    }

    public Expression getElement(Expression expression) {
        return content.get(expression.intValue());
    }
}
