package expression;

public class FunString extends Expression {
    private final Expression arg;

    public FunString(Expression arg) {
        this.arg = arg;
    }

    @Override
    public int intValue() {
        throw new RuntimeException("STRING function is not a number");
    }

    @Override
    public String stringValue() {
        return stringValue(arg);
    }

    private String stringValue(Expression arg) {
        return switch (arg.getType()) {
            case STRING -> arg.stringValue();
            case INTEGER -> Integer.toString(arg.intValue());
            case LIST -> {
                StringBuilder res = new StringBuilder("[");
                for (Expression e : arg.listValue().getContent())
                    res.append(stringValue(e)).append(", ");
                yield res + " ]";
            }
        };
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