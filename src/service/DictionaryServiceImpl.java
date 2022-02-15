package service;

import config.FileConfig;
import dao.FileDictionaryDao;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.FormatDictionaryException;
import exception.NotFoundWordDictionaryException;
import model.Phrase;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class DictionaryServiceImpl implements DictionaryService {

    private final FileDictionaryDao dictionaryDao;

    public String regex;

    public DictionaryServiceImpl(FileConfig fileConfig, String regex) {
        this.dictionaryDao = new FileDictionaryDao(fileConfig);
        this.regex = regex;
    }

    @Override
    public void addPhrase(Phrase phrase) {

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
            dictionaryDao.create(phrase);

        } catch (FormatDictionaryException | ExistWordDictionaryException exception){
            System.out.println(exception.getMessage());
        }
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
        Set<Phrase> dictionary = new HashSet<>();
        var result = dictionaryDao.read();
        if(!result.isEmpty()) {
            var strings = result.split("\n");

             dictionary = new HashSet<>();

            for(var string : strings){
                var pairsWords = string.split(" - ");
                dictionary.add(new Phrase(pairsWords[0], pairsWords[1]));
            }
        }
        return dictionary;
    }

    @Override
    public void checkPattern(String word) throws FormatDictionaryException {
        if(!word.matches(regex)){
            throw new FormatDictionaryException(regex);
        }
    }
}
