package service;

import dao.FileDictionaryDao;
import model.Dictionary;
import util.DictionaryType;

public class DictionaryCreator {

    public Dictionary createDictionary(DictionaryType dictionaryType){
        return new Dictionary(new FileDictionaryDao(dictionaryType),
                dictionaryType,
                new CheckFormatService());
    }
}
