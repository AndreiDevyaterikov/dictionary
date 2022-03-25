package model;

import dao.DictionaryDao;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.NotFoundWordDictionaryException;
import service.CheckFormatService;
import util.DictionaryType;

public class Dictionary {

    private final String EMPTY_DICTIONARY = "Empty dictionary";

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
            try {
                var phrase = dictionaryDao.create(new Phrase(word, translate));
                return new ResponseMessage<>(phrase);
            } catch (ExistWordDictionaryException exception){
                return new ResponseMessage<>(exception.getMessage());
            }
        }
        return new ResponseMessage<>(dictionaryType.getDescription());
    }

    public ResponseMessage deletePhrase(String word){

        var correctFormat = checkFormatService.checkWordTranslateDictionary(word, dictionaryType.getRegexWord());
        if(correctFormat){
            try {
                return new ResponseMessage<>(dictionaryDao.delete(word));
            } catch (EmptyDictionaryException | NotFoundWordDictionaryException exception){
                return new ResponseMessage<>(exception.getMessage());
            }
        }
        return new ResponseMessage<>(dictionaryType.getDescription());
    }

    public ResponseMessage editPhrase(String word, String newTranslate){

        var correctFormat = checkFormatService.checkWordTranslateDictionary(word, dictionaryType.getRegexWord());
        if(correctFormat){
            try {
                var updatedPhrase = dictionaryDao.update(new Phrase(word, newTranslate));
                return new ResponseMessage<>(updatedPhrase);
            } catch (EmptyDictionaryException | NotFoundWordDictionaryException exception) {
                return new ResponseMessage<>(exception.getMessage());
            }
        }
        return new ResponseMessage<>(dictionaryType.getDescription());
    }

    public ResponseMessage findByWord(String word){
        var correctFormat = checkFormatService.checkWordTranslateDictionary(word, dictionaryType.getRegexWord());
        if(correctFormat){
            var phrase = dictionaryDao.findByWord(word);
            if(phrase.isEmpty()){
                return new ResponseMessage<>("Not found word");
            }
            return new ResponseMessage<>(phrase.get());
        }
        return new ResponseMessage<>(dictionaryType.getDescription());
    }

    public ResponseMessage getDictionary(){
        var dictionary = dictionaryDao.read();
        if(dictionary.isEmpty()){
            return new ResponseMessage<>(EMPTY_DICTIONARY);
        }
        return new ResponseMessage<>(dictionary);
    }
}

