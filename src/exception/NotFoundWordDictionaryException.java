package exception;

public class NotFoundWordDictionaryException extends Exception{
    public NotFoundWordDictionaryException(String word) {
        super("Can't find " + word);
    }
}
