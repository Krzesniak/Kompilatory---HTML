package htmlable;

import java.util.stream.Collectors;

public class HtmlElement extends Htmlable{
    private final String name;

    public HtmlElement(String name) {
        this.name = name;
    }

    @Override
    public String eval() throws Exception {
        if(codeBlock != null) {
            //codeBlock.spreadParent();
            codeBlock.execute();
        }

        StringBuilder res = new StringBuilder("<" + name );
        String argumentsValue = arguments.entrySet().stream()
                .map(entry -> entry.getKey() + "=\"" + entry.getValue().stringValue()+"\"")
                .collect(Collectors.joining(" "));
        res.append(" ").append(argumentsValue).append(">\n");
        for(Htmlable h : htmlables)
            res.append(h.eval()).append("\n");
        res.append("</").append(name).append(">\n");

        return res.toString();
    }
}
