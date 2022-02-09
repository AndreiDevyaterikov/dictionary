import controller.Controller;
import exception.DictionaryFormatException;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.NotFoundWordDictionaryException;
import service.DictionaryFileStorage;
import service.DictionaryService;
import service.FileService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws DictionaryFormatException, EmptyDictionaryException, IOException, ExistWordDictionaryException, NotFoundWordDictionaryException {
        FileService fileService = new FileService();
        fileService.getFile("text.txt");

    }
}
