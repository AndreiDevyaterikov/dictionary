import controller.Controller;
import service.DictionarySelector;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new DictionarySelector());
        controller.selectDictionary();
    }
}
