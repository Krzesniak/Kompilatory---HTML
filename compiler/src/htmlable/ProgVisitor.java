package htmlable;

import gen.antlr.GrmBaseVisitor;
import gen.antlr.GrmParser;

import java.util.*;

public class ProgVisitor extends GrmBaseVisitor<Program> {
    @Override
    public Program visitProgram(GrmParser.ProgramContext ctx) {
        List<String> errors = new LinkedList<>();
        Program prg = new Program();
        prg.errors = errors;


        HashMap<String, ComponentDefinition> componentDefinitions = new HashMap<>();
        ComponentVisitor1 componentVisitor1 = new ComponentVisitor1(errors, componentDefinitions);
        componentVisitor1.visit(ctx.getChild(0));

        ComponentVisitor2 componentVisitor2 = new ComponentVisitor2(errors, componentDefinitions);
        componentVisitor2.visit(ctx.getChild(0));

        HtmlableVisitor htmlableVisitor = new HtmlableVisitor(errors, componentDefinitions);
        prg.htmlDocument = (HtmlElement) htmlableVisitor.visit(ctx.getChild(1));

        return prg;
    }
}
