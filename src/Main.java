import controller.Controller;
import exception.DictionaryFormatException;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.NotFoundWordDictionaryException;
import service.DictionaryFileStorage;
import service.DictionaryService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws DictionaryFormatException, EmptyDictionaryException, IOException, ExistWordDictionaryException, NotFoundWordDictionaryException {
        Controller controller = new Controller(new DictionaryService(new DictionaryFileStorage()));
        controller.selectDictionary();

    }
}
