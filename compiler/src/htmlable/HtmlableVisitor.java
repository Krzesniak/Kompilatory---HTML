package htmlable;

import expression.Expression;
import expression.ExpressionVisitor;
import expression.Variable;
import gen.antlr.GrmBaseVisitor;
import gen.antlr.GrmParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

public class HtmlableVisitor extends GrmBaseVisitor<Htmlable> {
    private Map<String, ComponentDefinition> componentDefinitions;

    private ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    public HtmlableVisitor() {
        componentDefinitions = new LinkedHashMap<>();
    }

    @Override
    public Htmlable visitHTML_element(GrmParser.HTML_elementContext ctx) {
        String name = ctx.getChild(0).toString();

        HtmlElement htmlElement = new HtmlElement(name);

        for (GrmParser.HtmlableContext hc : ctx.htmlable()) {
            Htmlable h = visit(hc);
            if (h != null)
                htmlElement.addHtmlable(h);
            else
                System.out.println("why null");
        }
        return htmlElement;
    }

    @Override
    public Htmlable visitHTML_inner(GrmParser.HTML_innerContext ctx) {
        Expression expression = expressionVisitor.visit(ctx.getChild(0));
        return new HtmlInner(expression);
    }

    @Override
    public Htmlable visitRepeat(GrmParser.RepeatContext ctx) {
        Expression arg = expressionVisitor.visit(ctx.getChild(2));
        Repeat repeat = new Repeat(arg);

        for (GrmParser.HtmlableContext hc : ctx.htmlable()) {
            Htmlable h = visit(hc);
            if (h != null)
                repeat.addHtmlable(h);
            else
                System.out.println("why null");
        }

        return repeat;
    }

    @Override
    public Htmlable visitEach(GrmParser.EachContext ctx) {
        String varName = ctx.getChild(5).getText();
        Variable var = new Variable(varName);

        ParseTree argsTree = ctx.getChild(2);
        LinkedList<Expression> args = new LinkedList<>();

        for (int i = 0; i < argsTree.getChildCount(); i += 2) {
            Expression arg = expressionVisitor.visit(argsTree.getChild(i));
            args.add(arg);
        }

        Each each = new Each(var, args);

        for (int i = 7; i < ctx.getChildCount() - 1; i++) {
            Htmlable h = visit(ctx.getChild(i));
            if (h != null)
                each.addHtmlable(h);
            else
                System.out.println("why null");
        }

        return each;
    }

    /*@Override
    public Htmlable visitComponents(GrmParser.ComponentsContext ctx) {
        return super.visitComponents(ctx);
    }*/

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
        ComponentDefinition definition = new ComponentDefinition(componentId, argsIds);

        for (int i = 5; i < ctx.getChildCount() - 1; i++) {
            Htmlable h = visit(ctx.getChild(i));
            definition.addHtmlable(h);
        }

        definition.spreadParent();

        if(componentDefinitions.containsKey(componentId)) {
            //TODO error
        }
        else {
            componentDefinitions.put(componentId, definition);
        }

        return definition;
    }

    @Override
    public Htmlable visitComponent(GrmParser.ComponentContext ctx) {
        String id = ctx.getChild(0).getText();
        ComponentDefinition definition = componentDefinitions.get(id);

        ParseTree argsTree = ctx.getChild(2);
        LinkedList<Expression> args = new LinkedList<>();

        for (int i = 0; i < argsTree.getChildCount(); i += 2) {
            Expression arg = expressionVisitor.visit(argsTree.getChild(i));
            args.add(arg);
        }

        return new Component(definition, args);
    }
}
