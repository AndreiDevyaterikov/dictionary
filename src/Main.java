import controller.Controller;
import service.DictionaryCreator;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new DictionaryCreator());
        controller.selectDictionary();
    }
}
