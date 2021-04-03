package htmlable;

import gen.antlr.GrmBaseVisitor;
import gen.antlr.GrmParser;

public class ProgVisitor extends GrmBaseVisitor<Program> {
    @Override
    public Program visitProgram(GrmParser.ProgramContext ctx) {
        Program prg = new Program();

        HtmlableVisitor htmlableVisitor = new HtmlableVisitor();

        htmlableVisitor.visit(ctx.getChild(0));
        prg.htmlDocument = (HtmlElement) htmlableVisitor.visit(ctx.getChild(1));

        return prg;
    }
}
