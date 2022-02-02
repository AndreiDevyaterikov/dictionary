package strategy;

import exception.DictionaryFormatException;
import exception.NotExistWordException;
import exception.WordExitException;

import java.io.IOException;
import java.util.Map;

public interface BaseStrategy {

    Map<String, String> add(String key, String value) throws WordExitException, DictionaryFormatException, IOException;
    void delete(String key) throws NotExistWordException, IOException;
    Map<String, String>  getDictionary() throws IOException;

}
