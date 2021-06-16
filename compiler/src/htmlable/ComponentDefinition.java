package htmlable;

import app.ProgramException;

import java.util.ArrayList;
import java.util.List;

public class ComponentDefinition extends Htmlable {
    String id;
    List<String> argsIds= new ArrayList<>();

    public ComponentDefinition(List<String> errors) {
        super(errors);
    }

    public int argIndex(String id) {
        for (int i = 0; i < argsIds.size(); i++)
            if (argsIds.get(i).equals(id))
                return i;

        return -1;
    }

    @Override
    public String eval() {
        try {
        StringBuilder res = new StringBuilder();
        for(Htmlable h : htmlables)
            res.append(h.eval()).append("\n");

        return res.toString();
        }
        catch (ProgramException pe) {
            errors.add(pe.getMessage());
            return "err";
        }
    }
}
