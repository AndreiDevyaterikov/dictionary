package model;

import dao.DictionaryDao;
import service.CheckFormatService;
import util.DictionaryType;

public class Dictionary {

    private final String EMPTY = "Empty dictionary";

    DictionaryDao dictionaryDao;
    DictionaryType dictionaryType;
    CheckFormatService checkFormatService;

    public Dictionary(DictionaryDao dictionaryDao, DictionaryType dictionaryType, CheckFormatService checkFormatService) {
        this.dictionaryDao = dictionaryDao;
        this.dictionaryType = dictionaryType;
        this.checkFormatService = checkFormatService;
    }

    public DictionaryType getDictionaryType(){
        return dictionaryType;
    }

    public ResponseMessage addPhrase(String word, String translate){

        var correctFormat = checkFormatService.checkWordTranslateDictionary(word, dictionaryType.getRegexWord());
        if(correctFormat){
            return new ResponseMessage<>(dictionaryDao.create(new Phrase(word, translate)));
        }
        return new ResponseMessage<>(dictionaryType.getDescription());
    }

    public ResponseMessage deletePhrase(String word){

        var correctFormat = checkFormatService.checkWordTranslateDictionary(word, dictionaryType.getRegexWord());
        if(correctFormat){
            return new ResponseMessage<>(dictionaryDao.delete(word));
        }
        return new ResponseMessage<>(dictionaryType.getDescription());
    }

    public ResponseMessage editPhrase(String word, String newTranslate){

        var correctFormat = checkFormatService.checkWordTranslateDictionary(word, dictionaryType.getRegexWord());
        if(correctFormat){
            return new ResponseMessage<>(dictionaryDao.update(new Phrase(word, newTranslate)));
        }
        return new ResponseMessage<>(dictionaryType.getDescription());
    }

    public ResponseMessage findByWord(String word){
        var correctFormat = checkFormatService.checkWordTranslateDictionary(word, dictionaryType.getRegexWord());
        if(correctFormat){
            return new ResponseMessage<>(dictionaryDao.findByWord(word));
        }
        return new ResponseMessage<>(dictionaryType.getDescription());
    }

    public ResponseMessage getDictionary(){
        var dictionary = dictionaryDao.read();
        if(dictionary.isEmpty()){
            return new ResponseMessage<>(EMPTY);
        }
        return new ResponseMessage<>(dictionary);
    }
}

