package expression;

import htmlable.Htmlable;

public abstract class Expression {
    public enum Type {
        INTEGER,
        STRING
    }

    public Htmlable parent;

    public abstract int intValue();
    public abstract String stringValue() ;
    public abstract Type getType() ;

    //probably evil
    public void spreadParent(){ }
}
