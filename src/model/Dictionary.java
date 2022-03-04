package model;

import dao.DictionaryDao;
import service.CheckFormatService;
import util.DictionaryType;

import java.util.Map;

public class Dictionary {


    DictionaryDao dictionaryDao;
    DictionaryType dictionaryType;
    CheckFormatService checkFormatService;

    public Dictionary(DictionaryDao dictionaryDao, DictionaryType dictionaryType, CheckFormatService checkFormatService) {
        this.dictionaryDao = dictionaryDao;
        this.dictionaryType = dictionaryType;
        this.checkFormatService = checkFormatService;
    }

    public String getDictionaryName(){
        return dictionaryType.getTitle();
    }

    public Phrase addPhrase(String word, String translate){

        checkFormatService.checkLanguageDictionary(word, dictionaryType.getRegex());

        return dictionaryDao.create(new Phrase(word, translate));
    }

    public Phrase deletePhrase(String word){

        checkFormatService.checkLanguageDictionary(word, dictionaryType.getRegex());

        return dictionaryDao.delete(word);
    }

    public Phrase editPhrase(String word, String newTranslate){

        checkFormatService.checkLanguageDictionary(word, dictionaryType.getRegex());

        return dictionaryDao.update(new Phrase(word, newTranslate));
    }

    public Phrase findByWord(String word){

        checkFormatService.checkLanguageDictionary(word, dictionaryType.getRegex());

        return dictionaryDao.findByWord(word);
    }

    public Map<String, String> getDictionary(){
        return dictionaryDao.read();
    }
}
