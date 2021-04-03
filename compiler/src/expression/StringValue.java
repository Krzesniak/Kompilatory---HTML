package expression;

public class StringValue extends Expression{
    private String value;

    public StringValue(String value){
        this.value = value;
    }

    @Override
    public int intValue(){
        //TODO: jakaś obsługa błędów czy coś
        return -34404;
    }

    @Override
    public String stringValue() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }
}
