package service;

import dao.FileDictionaryDao;
import exception.NotFoundWordDictionaryException;
import model.Phrase;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDictionaryService implements DictionaryService{

    static Logger LOGGER = Logger.getLogger(FileDictionaryService.class.getName());

    private final FileDictionaryDao fileDictionaryDao;

    public FileDictionaryService(FileDictionaryDao fileDictionaryDao) {
        this.fileDictionaryDao = fileDictionaryDao;
    }

    @Override
    public Phrase addPhrase(Phrase phrase) {
        return fileDictionaryDao.create(phrase);
    }

    @Override
    public Map<String, String> getResult() {
        return fileDictionaryDao.read();
    }

    @Override
    public Phrase editPhrase(Phrase phrase){

        return fileDictionaryDao.update(phrase);
    }

    @Override
    public Phrase deletePhrase(String word) {

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
