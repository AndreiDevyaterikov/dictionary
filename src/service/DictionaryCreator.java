package service;

import dao.FileDictionaryDao;
import model.Dictionary;
import util.DictionaryType;

import java.util.ArrayList;
import java.util.List;

public class DictionaryCreator {

    public List<Dictionary> getDictionaries(){
        List<Dictionary> dictionaries = new ArrayList<>();
        for(var dictionaryType : DictionaryType.values()){
            dictionaries.add(new Dictionary(new FileDictionaryDao(dictionaryType),
                    dictionaryType,
                    new CheckFormatService()));
        }
        return dictionaries;
    }
}
