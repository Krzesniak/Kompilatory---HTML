package htmlable;

import app.ProgramException;
import expression.Expression;

import java.util.List;

public class Component extends Htmlable{
    private final ComponentDefinition definition;
    private final List<Expression> args;

    public Component(List<String> errors, ComponentDefinition definition, List<Expression> args){
        super(errors);
        this.definition = definition;
        this.args = args;
        try {
            if (definition.argsIds.size() > args.size()) {
                throw new ProgramException("Too little arguments in  calling component " + definition.id);
            }
        }
        catch (ProgramException pe) {
            errors.add(pe.getMessage());
        }
    }

    @Override
    public Expression getVariable(String id) {
        int argIx = definition.argIndex(id);
        if(argIx == -1)
            return super.getVariable(id);

        try {
            return args.get(argIx);
        }
        catch (Exception e) {
            //errors.add("Argument not found");
            return null;
        }
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
        try {
            definition.parent = this;
            return definition.toString();
        }
        catch (ProgramException pe) {
            errors.add(pe.getMessage());
            return "ERR";
        }
    }
}
