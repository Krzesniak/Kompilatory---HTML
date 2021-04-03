package expression;

public class Variable extends Expression{
    String id;

    private String valString;
    private int valInt;
    private Type type;

    public Variable(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setValue(int i){
        type = Type.INTEGER;
        valInt = i;
    }
    public void setValue(String s) {
        type = Type.STRING;
        valString = s;
    }

    @Override
    public int intValue() {
        return valInt;
    }

    @Override
    public String stringValue() {
        return valString;
    }

    @Override
    public Type getType() {
        return type;
    }
}
