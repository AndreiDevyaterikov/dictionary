package service;

import dao.FileDictionaryDao;
import exception.FormatDictionaryException;
import exception.NotFoundWordDictionaryException;
import model.Phrase;
import util.DictionaryType;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDictionaryService implements DictionaryService{

    static Logger LOGGER = Logger.getLogger(FileDictionaryService.class.getName());

    private final DictionaryType dictionaryType;
    private final CheckFormatService checkFormatService;
    private final FileDictionaryDao fileDictionaryDao;

    public FileDictionaryService(DictionaryType dictionaryType, CheckFormatService checkFormatService, FileDictionaryDao fileDictionaryDao) {
        this.dictionaryType = dictionaryType;
        this.checkFormatService = checkFormatService;
        this.fileDictionaryDao = fileDictionaryDao;
    }

    @Override
    public Phrase addPhrase(Phrase phrase) {

        try {
            var checkResult = checkFormatService.checkLanguageDictionary(phrase.getWord(), dictionaryType.getRegex());
            if(checkResult){
                return fileDictionaryDao.create(phrase);
            } else {
                throw new FormatDictionaryException(dictionaryType.getRegex());
            }

        } catch (FormatDictionaryException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }

        return phrase;
    }

    @Override
    public Map<String, String> getResult() {
        return fileDictionaryDao.read();
    }

    @Override
    public Phrase editPhrase(Phrase phrase){

        try {
            var checkResult = checkFormatService.checkLanguageDictionary(phrase.getWord(), dictionaryType.getRegex());
            if(!checkResult){
                throw new FormatDictionaryException(phrase.getWord());
            }

        } catch (FormatDictionaryException  exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return fileDictionaryDao.update(phrase);
    }

    @Override
    public Phrase deletePhrase(String word) {
        try {
            var checkResult = checkFormatService.checkLanguageDictionary(word, dictionaryType.getRegex());
            if(!checkResult){
                throw new FormatDictionaryException(word);
            }
        } catch (FormatDictionaryException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return fileDictionaryDao.delete(word);
    }

    @Override
    public Phrase findByWord(String word) {
        var phrase = fileDictionaryDao.findByWord(word);
        try {
            if(phrase == null){
                throw new NotFoundWordDictionaryException(word);
            }
        } catch (NotFoundWordDictionaryException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return phrase;
    }
}
