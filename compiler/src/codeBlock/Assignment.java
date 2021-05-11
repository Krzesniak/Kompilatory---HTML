package codeBlock;


import expression.Expression;
import expression.Variable;
import htmlable.Htmlable;

public class Assignment {
    public Htmlable parent;
    private String leftSideId;
    private Expression rightSide;

    Assignment(String id, Expression rightSide) {
        this.leftSideId = id;
        this.rightSide = rightSide;
    }

    public void execute(){
        Variable variable = (Variable) parent.getVariable(leftSideId);
        if(variable == null) {
            variable = new Variable(leftSideId);
            parent.addVariable(variable);
            variable.parent = parent;
        }

        if(rightSide.getType() == Expression.Type.INTEGER)
            variable.setValue(rightSide.intValue());
        else
            variable.setValue(rightSide.stringValue());
    }

    public void spreadParent() {
        rightSide.parent = parent;
        rightSide.spreadParent();
    }
}
