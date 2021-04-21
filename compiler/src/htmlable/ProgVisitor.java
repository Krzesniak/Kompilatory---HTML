package htmlable;

import gen.antlr.GrmBaseVisitor;
import gen.antlr.GrmParser;

import java.util.LinkedList;
import java.util.List;

public class ProgVisitor extends GrmBaseVisitor<Program> {
    @Override
    public Program visitProgram(GrmParser.ProgramContext ctx) {
        List<String> errors = new LinkedList<>();
        Program prg = new Program();
        prg.errors = errors;

        HtmlableVisitor htmlableVisitor = new HtmlableVisitor(errors);

        htmlableVisitor.visit(ctx.getChild(0));
        prg.htmlDocument = (HtmlElement) htmlableVisitor.visit(ctx.getChild(1));

        return prg;
    }
}
