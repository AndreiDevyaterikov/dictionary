import controller.Controller;
import exception.DictionaryFormatException;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.NotFoundWordDictionaryException;
import service.DictionaryService;
import service.DictionaryFileStorage;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws
            IOException,
            DictionaryFormatException,
            ExistWordDictionaryException,
            EmptyDictionaryException,
            NotFoundWordDictionaryException {

        Controller controller = new Controller(new DictionaryService(new DictionaryFileStorage()));
        System.out.println(controller.selectDictionary());

    }
}
