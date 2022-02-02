import controller.Controller;
import exception.DictionaryFormatException;
import exception.NotExistWordException;
import exception.WordExitException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws DictionaryFormatException, WordExitException, NotExistWordException, IOException {
        Controller controller = new Controller();
        controller.control();

    }
}
