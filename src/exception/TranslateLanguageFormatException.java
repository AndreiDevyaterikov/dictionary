package exception;

public class TranslateLanguageFormatException extends Exception{
    public TranslateLanguageFormatException() {
        super("Translate can't be equals dictionary language");
    }
}
