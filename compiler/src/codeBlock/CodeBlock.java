package codeBlock;

import htmlable.Htmlable;

import java.util.List;

public class CodeBlock {
    public Htmlable parent;
    private List<Assignment> assignmentList;

    CodeBlock(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public void execute() {
        for(Assignment assignment : assignmentList) {
            assignment.execute();
        }
    }

    public void spreadParent() {
        for(Assignment assignment : assignmentList) {
            assignment.parent = parent;
            assignment.spreadParent();
        }
    }
}
