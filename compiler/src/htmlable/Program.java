package htmlable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Program {
    public HtmlElement htmlDocument;
    private Map<String, ComponentDefinition> componentDefinitions;
    public List<String> errors;

    public Program(){
        componentDefinitions = new LinkedHashMap<>();
    }
}
