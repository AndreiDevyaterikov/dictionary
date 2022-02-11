package exception;

public class EmptyDictionaryException extends Exception{
    public EmptyDictionaryException() {
        super("Empty dictionary");
    }
}
