package expression;

public class ListElement extends Expression{
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
        return getElement().stringValue();
    }

    @Override
    public Liste listValue(){
        return getElement().listValue();
    }

    @Override
    public Type getType() {
        return getElement().getType();
    }

    @Override public void spreadParent(){
        this.index.parent = parent;
        this.index.spreadParent();

        this.liste.parent = parent;
    }

    private Expression getElement() {
        return liste.listValue().getElement(index);
    }
}
