package htmlable;

import expression.Expression;
import expression.Variable;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Htmlable {
    List<Htmlable> htmlables;
    Map<String, Variable> variables;

    public Htmlable parent;

    Htmlable() {
        htmlables = new LinkedList<>();
        variables = new LinkedHashMap<>();
    }

    void addHtmlable(Htmlable h) {
        htmlables.add(h);
    }

    void addVariable(Variable v) {
        variables.put(v.getId(), v);
    }

    public Expression getVariable(String id)  {
        if(variables.containsKey(id))
            return variables.get(id);

        return parent.getVariable(id);
    }

    public void spreadParent(){
        for(Variable v : variables.values()) {
            v.parent = this;
            v.spreadParent();
        }

        for(Htmlable h : htmlables) {
            h.parent = this;
            h.spreadParent();
        }
    }

    @Override
    public abstract String toString();
}
