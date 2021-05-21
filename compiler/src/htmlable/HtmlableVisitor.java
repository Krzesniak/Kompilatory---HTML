package htmlable;

import codeBlock.CodeBlock;
import codeBlock.CodeBlockVisitor;
import expression.Expression;
import expression.ExpressionVisitor;
import expression.StringValue;
import expression.Variable;
import gen.antlr.GrmBaseVisitor;
import gen.antlr.GrmParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class HtmlableVisitor extends GrmBaseVisitor<Htmlable> {
    Map<String, ComponentDefinition> componentDefinitions;
    List<String> errors;

    private ExpressionVisitor expressionVisitor = new ExpressionVisitor();
    private CodeBlockVisitor codeBlockVisitor = new CodeBlockVisitor();

    public HtmlableVisitor(List<String> errors, Map<String, ComponentDefinition> componentDefinitions) {
        this.componentDefinitions = componentDefinitions;
        this.errors = errors;
    }

    @Override
    public Htmlable visitHTML_element(GrmParser.HTML_elementContext ctx) {
        String name = ctx.getChild(0).toString();

        HtmlElement htmlElement = new HtmlElement(name);
        addArguments(htmlElement, ctx);

        CodeBlock codeBlock = null;
        if(ctx.code_block() != null)
            codeBlock = codeBlockVisitor.visit(ctx.code_block());
        htmlElement.codeBlock = codeBlock;

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
        setLineAndColumn(repeat, ctx.REPEAT().getSymbol());

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

    @Override
    public Htmlable visitSwitche(GrmParser.SwitcheContext ctx) {
        final Expression expression = expressionVisitor.visit(ctx.getChild(2));
        Htmlable elseBlock;
        try {
            elseBlock = visit(ctx.getChild(ctx.getChildCount() - 2).getChild(2));
        } catch(final Exception e) {
            throw new RuntimeException("Switch expression has to have an else block");
        }
        final var htmlables = ctx.children.stream()
                .skip(5)
                .takeWhile(it -> !it.getChild(0).getText().equals("else"))
                .collect(Collectors.toMap(it -> expressionVisitor.visit(it.getChild(0)), it -> visit(it.getChild(2))));

        return new Switch(expression, htmlables, elseBlock);
    }

    @Override
    public Htmlable visitComponent(GrmParser.ComponentContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        int line = idToken.getLine();
        int column = idToken.getCharPositionInLine() + 1;

        String id = ctx.getChild(0).getText();
        ComponentDefinition definition = componentDefinitions.get(id);
        if (definition == null)
            errors.add("Error: component " + id + " referenced at (" + line + "," + column + ") doesn't exists");

        ParseTree argsTree = ctx.getChild(2);
        LinkedList<Expression> args = new LinkedList<>();

        for (int i = 0; i < argsTree.getChildCount(); i += 2) {
            Expression arg = expressionVisitor.visit(argsTree.getChild(i));
            args.add(arg);
        }

        return new Component(definition, args);
    }

    public void addArguments(HtmlElement htmlElement, GrmParser.HTML_elementContext ctx){
        ParseTree child = ctx.getChild(1);
        List<ParseTree> children = ((GrmParser.ArgsContext) child).children;
        if(children != null){
            for (int i=0; i<children.size(); i++) {
                if(children.get(i).toString().equals("=")){
                   String first = children.get(i-1).toString();
                   Expression expression =  expressionVisitor.visit(children.get(i+1));
                   htmlElement.addArgument(first, expression);
                }
            }
        }


    }

    private static void setLineAndColumn(Htmlable htmlable, Token idToken) {
        int line = idToken.getLine();
        int column = idToken.getCharPositionInLine() + 1;

        htmlable.line = line;
        htmlable.column = column;
    }
}
