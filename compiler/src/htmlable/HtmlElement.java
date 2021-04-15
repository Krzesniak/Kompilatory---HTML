package htmlable;

public class HtmlElement extends Htmlable{
    private final String name;

    public HtmlElement(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("<" + name + ">\n");
        for(Htmlable h : htmlables)
            res.append(h.toString()).append("\n");
        res.append("</").append(name).append(">\n");

        return res.toString();
    }
}
