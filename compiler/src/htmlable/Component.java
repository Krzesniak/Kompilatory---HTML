package htmlable;

import expression.Expression;
import expression.Variable;

import java.util.List;

public class Component extends Htmlable{
    private ComponentDefinition definition;
    private List<Expression> args;

    public Component(ComponentDefinition definition, List<Expression> args){
        this.definition = definition;
        this.args = args;
    }

    @Override
    public Expression getVariable(String id) {
        int argIx = definition.argIndex(id);
        if(argIx == -1)
            return super.getVariable(id);

        return args.get(argIx);
    }

    @Override
    public void spreadParent() {
        super.spreadParent();
        for(Expression arg : args) {
            arg.parent = this;
            arg.spreadParent();
        }
    }

    @Override
    public String toString() {
        definition.parent = this;
        return definition.toString();
    }
}
