package exception;

public class FormatDictionaryException extends Exception {
    public FormatDictionaryException(String regex) {
        super("Correct format of dictionary is - " + regex);
    }
}
