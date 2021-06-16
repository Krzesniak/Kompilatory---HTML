package app;

import org.antlr.v4.runtime.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ErrorListner extends BaseErrorListener {
    public static boolean hasError = false;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        hasError = true;

        List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        System.out.println("Syntax Error!");
        System.out.println("Token " + "\"" + ((Token) offendingSymbol).getText() + "\""
                            +
                            " (line " + line + ", column " + (charPositionInLine + 1) + ")"
                            +
                            ": " + msg);

        System.out.println("Rule Stack:: " + stack);
    }
}
