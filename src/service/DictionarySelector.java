package service;

import dao.FileDictionaryDao;
import model.Dictionary;
import util.DictionaryType;

public class DictionarySelector {

    public Dictionary selectDictionary(DictionaryType dictionaryType){
        return new Dictionary(dictionaryType,
                new FileDictionaryService(new FileDictionaryDao(dictionaryType)),
                new CheckFormatService());
    }
}
