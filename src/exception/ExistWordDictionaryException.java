package exception;

public class ExistWordDictionaryException extends Exception{
    public ExistWordDictionaryException(String word, String translate) {
        super("Word " + word + " already exists with translate - " + translate);
    }
}
