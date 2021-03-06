package htmlable;

import codeBlock.CodeBlock;
import expression.Expression;
import expression.Variable;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Htmlable {
    List<Htmlable> htmlables;
    Map<String, Variable> variables;
    Map<String, Expression> arguments;
    CodeBlock codeBlock;
    List<String> errors;

    public int line;
    public int column;

    public Htmlable parent;

    public boolean hasError = false;

    Htmlable(List<String> errors) {
        htmlables = new LinkedList<>();
        variables = new LinkedHashMap<>();
        arguments = new LinkedHashMap<>();
        this.errors = errors;
    }

    void addHtmlable(Htmlable h) {
        htmlables.add(h);
    }

    public void addVariable(Variable v) {
        variables.put(v.getId(), v);
    }

    void addArgument(String key, Expression value) {
        arguments.put(key, value);
    }

    public Expression getVariable(String id) {
        if (variables.containsKey(id))
            return variables.get(id);

        if (parent == null)
            return null;
        return parent.getVariable(id);
    }

    public void spreadParent() {
        if (codeBlock != null) {
            codeBlock.parent = this;
            codeBlock.spreadParent();
        }

        for (Variable v : variables.values()) {
            v.parent = this;
            v.spreadParent();
        }

        for (Htmlable h : htmlables) {
            h.parent = this;
            h.spreadParent();
        }

        for (Expression e : arguments.values()) {
            e.parent = this;
            e.spreadParent();
        }
    }

    public abstract String eval();

    public String toString() {
        if(!hasError)
            return eval();
        return "!!!ERROR!!!";
    }
}
