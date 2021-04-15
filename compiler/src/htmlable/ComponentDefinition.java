package htmlable;

import java.util.List;

public class ComponentDefinition extends Htmlable {
    private String id;
    private List<String> argsIds;

    public ComponentDefinition(String id, List<String> argsIds) {
        this.id = id;
        this.argsIds = argsIds;
        System.out.println("ARGS " + argsIds.toString());
    }

    public int argIndex(String id) {
        for (int i = 0; i < argsIds.size(); i++)
            if (argsIds.get(i).equals(id))
                return i;

        return -1;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(Htmlable h : htmlables)
            res.append(h.toString()).append("\n");

        return res.toString();
    }
}
