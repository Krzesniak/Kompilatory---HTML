package expression;

import gen.antlr.GrmBaseVisitor;
import gen.antlr.GrmParser;

public class ExpressionVisitor extends GrmBaseVisitor<Expression> {

    @Override
    public Expression visitString(GrmParser.StringContext ctx) {
        String text = ctx.getChild(0).getText();
        text = text.substring(1, text.length() - 1);
        return new StringValue(text);
    }

    @Override
    public Expression visitNumber(GrmParser.NumberContext ctx) {
        String text = ctx.getChild(0).getText();
        return new NumberValue(Integer.parseInt(text));
    }

    @Override
    public Expression visitVar(GrmParser.VarContext ctx) {
        String id = ctx.getChild(0).getText();
        id = id.substring(1); //removing $

        return new VariableReference(id);
    }

    @Override
    public Expression visitAdd(GrmParser.AddContext ctx) {
        Expression left = visit(ctx.getChild(0));
        Expression right = visit(ctx.getChild(2));
        return new Addition(left, right);
    }

    @Override
    public Expression visitEqual(GrmParser.EqualContext ctx) {
        Expression left = visit(ctx.getChild(0));
        Expression right = visit(ctx.getChild(2));
        return new Equals(left, right);
    }

    @Override
    public Expression visitModulo(GrmParser.ModuloContext ctx) {
        Expression left = visit(ctx.getChild(0));
        Expression right = visit(ctx.getChild(2));
        return new Modulo(left, right);
    }

    @Override
    public Expression visitExpr_args(GrmParser.Expr_argsContext ctx) {
        return super.visitExpr_args(ctx);
    }
}
