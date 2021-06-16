package htmlable;

import gen.antlr.GrmBaseVisitor;
import gen.antlr.GrmParser;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.List;

public class ComponentVisitor1 extends GrmBaseVisitor<Htmlable> {
    private List<String> errors;
    private HashMap<String, ComponentDefinition> componentDefinitions;

    public ComponentVisitor1(List<String> errors, HashMap<String, ComponentDefinition> componentDefinitions) {
        this.errors = errors;
        this.componentDefinitions = componentDefinitions;
    }

    @Override
    public Htmlable visitComponent_definition(GrmParser.Component_definitionContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        int line = idToken.getLine();
        int column = idToken.getCharPositionInLine() + 1;

        String componentId = ctx.getChild(0).getText();

        if(componentDefinitions.containsKey(componentId)) {
            errors.add("Error: component " + componentId + " redefined at (" + line + "," + column + ")");
        }
        else {
            ComponentDefinition componentDefinition = new ComponentDefinition(errors);
            componentDefinitions.put(componentId, componentDefinition);
        }

        return null;
    }
}
