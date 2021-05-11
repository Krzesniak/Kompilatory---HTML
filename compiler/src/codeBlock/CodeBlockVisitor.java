package codeBlock;

import gen.antlr.GrmBaseVisitor;
import gen.antlr.GrmParser;

import java.util.LinkedList;
import java.util.List;

public class CodeBlockVisitor extends GrmBaseVisitor<CodeBlock> {
    private final AssignmentVisitor assignmentVisitor = new AssignmentVisitor();
    @Override
    public CodeBlock visitCode_block(GrmParser.Code_blockContext ctx) {

        List<Assignment> assignmentList = new LinkedList<>();
        for(GrmParser.AssignmentContext actx : ctx.assignment()){
            assignmentList.add(assignmentVisitor.visit(actx));
        }

        return new CodeBlock(assignmentList);
    }
}
