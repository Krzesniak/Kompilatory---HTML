package expression;

public class Variable extends Expression {
    String id;

    private String valString;
    private int valInt;
    private Liste valList;
    private Type type;

    public Variable(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setValue(int i) {
        type = Type.INTEGER;
        valInt = i;
    }

    public void setValue(String s) {
        type = Type.STRING;
        valString = s;
    }

    public void setValue(Liste liste) {
        type = Type.LIST;
        valList = liste;
    }

    @Override
    public int intValue() {
        if (type != Type.INTEGER)
            throw new RuntimeException(String.format("%s is not a number", id));
        return valInt;
    }

    @Override
    public String stringValue() {
        if (type != Type.STRING)
            throw new RuntimeException(String.format("%s is not a string", id));
        return valString;
    }

    @Override
    public Liste listValue() {
        if (type != Type.LIST)
            throw new RuntimeException(String.format("%s is not a list", id));
        return valList;
    }

    @Override
    public Type getType() {
        return type;
    }
}
