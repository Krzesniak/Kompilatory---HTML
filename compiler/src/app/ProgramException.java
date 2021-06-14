package app;

public class ProgramException extends RuntimeException {
    public ProgramException(String msg) {
        super(msg);
    }

    public ProgramException(int line, int column, String restOfMessage) {
        this(String.format("Error at %d,%d.%s", line, column, restOfMessage));
    }
}
