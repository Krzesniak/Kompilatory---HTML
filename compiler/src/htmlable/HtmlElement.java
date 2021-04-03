package htmlable;

public class HtmlElement extends Htmlable{
    String name;

    HtmlElement(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String res = "<" + name + ">\n";
        for(Htmlable h : htmlables)
            res += h.toString() + "\n";
        res += "</" + name + ">\n";

        return res;
    }
}
