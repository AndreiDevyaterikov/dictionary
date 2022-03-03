package service;

import dao.FileDictionaryDao;
import model.Dictionary;
import util.DictionaryType;

public class DictionaryCreator {

    public Dictionary createDictionary(DictionaryType dictionaryType){
        return new Dictionary(dictionaryType,
                new FileDictionaryService(new FileDictionaryDao(dictionaryType)),
                new CheckFormatService());
    }
}
