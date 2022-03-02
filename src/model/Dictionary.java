package model;

import service.CheckFormatService;
import service.DictionaryService;
import util.DictionaryType;

import java.util.Map;

public class Dictionary {


    DictionaryType dictionaryType;
    DictionaryService dictionaryService;
    CheckFormatService checkFormatService;

    public Dictionary(DictionaryType dictionaryType, DictionaryService dictionaryService, CheckFormatService checkFormatService) {
        this.dictionaryType = dictionaryType;
        this.dictionaryService = dictionaryService;
        this.checkFormatService = checkFormatService;
    }

    public String getDictionaryName(){
        return dictionaryType.getName();
    }

    public Phrase addPhrase(String word, String translate){

        checkFormatService.checkLanguageDictionary(word, dictionaryType.getRegex());

        return dictionaryService.addPhrase(new Phrase(word, translate));
    }

    public Phrase deletePhrase(String word){

        checkFormatService.checkLanguageDictionary(word, dictionaryType.getRegex());

        return dictionaryService.deletePhrase(word);
    }

    public Phrase editPhrase(String word, String newTranslate){

        checkFormatService.checkLanguageDictionary(word, dictionaryType.getRegex());

        return dictionaryService.editPhrase(new Phrase(word, newTranslate));
    }

    public Phrase findByWord(String word){

        checkFormatService.checkLanguageDictionary(word, dictionaryType.getRegex());

        return dictionaryService.findByWord(word);
    }

    public Map<String, String> getDictionary(){
        return dictionaryService.getResult();
    }
}
