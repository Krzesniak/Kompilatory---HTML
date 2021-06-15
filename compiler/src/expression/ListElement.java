package expression;

import app.ProgramException;

public class ListElement extends Expression {
    private final Expression liste;
    private final Expression index;

    ListElement(Expression liste, Expression index) {
        this.liste = liste;
        this.index = index;
    }

    @Override
    public int intValue() {
        return getElement().intValue();
    }

    @Override
    public String stringValue() {
        return switch (getElement().getType()) {
            case STRING -> getElement().stringValue();
            default -> throw new ProgramException(parent.line, parent.column, "Cannot cast list element to string. Maybe use STRING function?");
        };
    }

    @Override
    public Liste listValue() {
        return getElement().listValue();
    }

    @Override
    public Type getType() {
        return getElement().getType();
    }

    @Override
    public void spreadParent() {
        this.index.parent = parent;
        this.index.spreadParent();

        this.liste.parent = parent;
        this.liste.spreadParent();
    }

    private Expression getElement() {
        if (!index.getType().equals(Type.INTEGER))
            throw new ProgramException(String.format("Error at %s, %s.List index must be integer", parent.line, parent.column));
        if (index.intValue() < 0 || index.intValue() >= liste.listValue().getSize())
            throw new IndexOutOfBoundsException(String.format("Error at %s, %s. Index %d out of bounds", parent.line, parent.column, index.intValue()));
        return liste.listValue().getElement(index);
    }
}
