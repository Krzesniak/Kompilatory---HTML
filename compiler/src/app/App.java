package app;

import gen.antlr.GrmLexer;
import gen.antlr.GrmParser;
import htmlable.ProgVisitor;
import htmlable.Program;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length == 0 || !Files.exists(Paths.get(args[0]))) {
            System.out.println("Please provide a valid file path");
            return;
        }
        String fileName = args[0];
        GrmParser parser = getParser(fileName);

        ParseTree antlrAST = parser.prog();

        ProgVisitor progVisitor = new ProgVisitor();
        Program prog;
        try {
            prog = progVisitor.visit(antlrAST);
        } catch (ProgramException e) {
            System.out.println(e.getMessage());
            return;
        }
        prog.htmlDocument.spreadParent();

        if (!prog.errors.isEmpty()) {
            for (String err : prog.errors)
                System.out.println(err);
        } else {
            try {
                String result = prog.htmlDocument.eval();
                System.out.println(result);
                Files.writeString(Paths.get("index.html"), result);
            } catch (ProgramException e) {
                System.out.println(e.getMessage());
            }
        }
        //System.out.println(prog.htmlDocument.toString());
    }

    private static GrmParser getParser(String filename) {
        GrmParser parser = null;

        try {
            CharStream input = CharStreams.fromFileName(filename);
            GrmLexer lexer = new GrmLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new GrmParser(tokens);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parser;
    }
}
