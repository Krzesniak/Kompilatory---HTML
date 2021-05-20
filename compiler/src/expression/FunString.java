package expression;

public class FunString extends Expression {
    private final Expression arg;

    public FunString(Expression arg) {
        this.arg = arg;
    }

    @Override
    public int intValue() {
        throw new RuntimeException();
    }

    @Override
    public String stringValue() {
        return stringValue(arg);
    }

    private String stringValue(Expression arg) {
        if(arg.getType() == Type.INTEGER)
            return Integer.toString(arg.intValue());
        else if(arg.getType() == Type.STRING)
            return arg.stringValue();
        else {
            String res = "[";
            for(Expression e : arg.listValue().getContent())
                res += stringValue(e) + ", ";
            return res + " ]";
        }
    }

    @Override
    public Expression.Type getType() {
        return Type.STRING;
    }

    @Override
    public void spreadParent() {
        arg.parent = parent;
        arg.spreadParent();
    }
}