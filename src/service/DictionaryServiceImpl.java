package service;

import config.FileConfig;
import dao.FileDictionaryDao;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.FormatDictionaryException;
import exception.NotFoundWordDictionaryException;
import model.Phrase;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DictionaryServiceImpl implements DictionaryService {

    static Logger LOGGER = Logger.getLogger(DictionaryServiceImpl.class.getName());

    private final FileDictionaryDao dictionaryDao;

    public String regex;

    public DictionaryServiceImpl(FileConfig fileConfig, String regex) {
        this.dictionaryDao = new FileDictionaryDao(fileConfig);
        this.regex = regex;
    }

    @Override
    public Phrase addPhrase(Phrase phrase) {

        try {
            checkPattern(phrase.getWord());

            var dictionary = getResult();

            if(!dictionary.isEmpty()){
                for(Phrase dictionaryPhrase : dictionary){
                    if(phrase.getWord().equals(dictionaryPhrase.getWord())) {
                        throw new ExistWordDictionaryException(phrase.getWord(), dictionaryPhrase.getTranslate());
                    }
                }
            }

        } catch (FormatDictionaryException | ExistWordDictionaryException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return dictionaryDao.create(phrase);
    }

    //TODO Исправить - не работает
    @Override
    public void deletePhrase(String word) {

        try {
            checkPattern(word);

            var dictionary = getResult();

            if(dictionary.isEmpty()){
                throw new EmptyDictionaryException();
            }

            for(var phrase : dictionary){
                if(word.equals(phrase.getWord())){
                    dictionary.remove(phrase);
                } else {
                    throw new NotFoundWordDictionaryException(word);
                }
            }

            for(var pairWords : dictionary){
                dictionaryDao.create(pairWords);
            }

        } catch (FormatDictionaryException  | NotFoundWordDictionaryException | EmptyDictionaryException exception){
            System.out.println(exception.getMessage());
        }


    }

    @Override
    public void editPhrase(Phrase phrase) {

        var dictionary = getResult();

        try {
            checkPattern(phrase.getWord());

            if(dictionary.isEmpty()){
                throw new EmptyDictionaryException();
            }

            for (var dictionaryPhrase : dictionary){
                if(!phrase.getWord().equals(dictionaryPhrase.getWord())){
                    throw new NotFoundWordDictionaryException(phrase.getWord());
                } else {
                    dictionary.remove(dictionaryPhrase);
                    dictionary.add(phrase);
                }
            }

        } catch (FormatDictionaryException | NotFoundWordDictionaryException | EmptyDictionaryException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void printDictionary() {
        getResult().forEach(System.out::println);
    }

    @Override
    public Set<Phrase> getResult() {
        return dictionaryDao.read();
    }

    @Override
    public void checkPattern(String word) throws FormatDictionaryException {
        if(!word.matches(regex)){
            throw new FormatDictionaryException(regex);
        }
    }
}
