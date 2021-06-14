package htmlable;

import app.ProgramException;
import expression.Expression;

import java.util.List;

public class Component extends Htmlable{
    private final ComponentDefinition definition;
    private final List<Expression> args;

    public Component(ComponentDefinition definition, List<Expression> args){
        if(definition.argsIds.size()>args.size()){
            throw new ProgramException(parent.line, parent.column, "Too little arguments");
        }
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
    public String eval() {
        definition.parent = this;
        return definition.toString();
    }
}
