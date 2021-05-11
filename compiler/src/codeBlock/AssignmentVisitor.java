package codeBlock;

import expression.Expression;
import expression.ExpressionVisitor;
import gen.antlr.GrmBaseVisitor;
import gen.antlr.GrmParser;

public class AssignmentVisitor extends GrmBaseVisitor<Assignment> {
    private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    @Override
    public Assignment visitAssignment(GrmParser.AssignmentContext ctx) {
        String id = ctx.getChild(0).getText().substring(1);
        Expression rightSide = expressionVisitor.visit(ctx.getChild(2));
        return new Assignment(id, rightSide);
    }
}
