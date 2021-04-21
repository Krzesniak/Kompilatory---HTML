package htmlable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Program {
    public HtmlElement htmlDocument;
    private Map<String, ComponentDefinition> componentDefinitions;
    public List<String> errors;
    public static Htmlable currentlyVisitedNode;

    public Program(){
        componentDefinitions = new LinkedHashMap<>();
    }
}
