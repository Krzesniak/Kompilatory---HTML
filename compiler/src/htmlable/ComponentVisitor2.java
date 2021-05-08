package htmlable;

import gen.antlr.GrmParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ComponentVisitor2 extends HtmlableVisitor{

    public ComponentVisitor2(List<String> errors, HashMap<String, ComponentDefinition> componentDefinitions) {
        super(errors, componentDefinitions);
    }

    @Override
    public Htmlable visitComponent_definition(GrmParser.Component_definitionContext ctx) {
        String componentId = ctx.getChild(0).getText();

        ParseTree argsTree = ctx.getChild(2);
        List<String> argsIds = new LinkedList<>();

        if(argsTree!=null) {
            for (int i = 0; i < argsTree.getChildCount(); i += 2) {
                String id = argsTree.getChild(i).getText();
                argsIds.add(id);
            }
        }
        ComponentDefinition definition = componentDefinitions.get(componentId);
        definition.id = componentId;
        definition.argsIds = argsIds;

        for (int i = 5; i < ctx.getChildCount() - 1; i++) {
            Htmlable h = visit(ctx.getChild(i));
            definition.addHtmlable(h);
        }

        definition.spreadParent();

        return null;
    }
}
