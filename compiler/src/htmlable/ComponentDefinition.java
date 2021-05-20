package htmlable;

import java.util.List;

public class ComponentDefinition extends Htmlable {
    String id;
    List<String> argsIds;

    public ComponentDefinition() {
    }

    public int argIndex(String id) {
        for (int i = 0; i < argsIds.size(); i++)
            if (argsIds.get(i).equals(id))
                return i;

        return -1;
    }

    @Override
    public String eval() {
        StringBuilder res = new StringBuilder();
        for(Htmlable h : htmlables)
            res.append(h.toString()).append("\n");

        return res.toString();
    }
}
