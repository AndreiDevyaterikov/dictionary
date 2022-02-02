package model;

import strategy.BaseStrategy;
import exception.DictionaryFormatException;
import exception.NotExistWordException;
import exception.WordExitException;

import java.io.IOException;
import java.util.Map;

public class BaseDictionary {

    BaseStrategy baseStrategy;

    private String pathFile;

    public BaseDictionary(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public Map<String, String> add(String key, String value) throws WordExitException, DictionaryFormatException, IOException {
       return baseStrategy.add(key, value);
    }

    public void delete(String key) throws NotExistWordException, IOException {
        baseStrategy.delete(key);
    }

    public Map<String, String> getDictionary() throws IOException {
        return baseStrategy.getDictionary();
    }
}
