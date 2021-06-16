package htmlable;

import app.ProgramException;
import expression.Expression;
import expression.Variable;

import java.util.List;

public class HtmlInner extends Htmlable {
    Expression expression;

    HtmlInner(List<String> errors, Expression expression) {
        super(errors);
        expression.parent = this;
        this.expression = expression;
    }

    @Override
    public void spreadParent(){
        expression.parent = this;
        expression.spreadParent();
    }

    @Override
    public String eval(){
        try {
            return expression.stringValue() + "\n";
        }
        catch (ProgramException pe) {
            errors.add(pe.getMessage());
            return "ERROR";
        }
    }
}
