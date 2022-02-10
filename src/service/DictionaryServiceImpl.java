package service;

import config.FileConfig;
import dao.DictionaryDaoImpl;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.NotFoundWordDictionaryException;
import model.Phrase;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class DictionaryServiceImpl implements DictionaryService {

    public String regex;

    private final DictionaryDaoImpl dictionaryDao;

    public DictionaryServiceImpl(FileConfig fileConfig, String regex) {
        this.dictionaryDao = new DictionaryDaoImpl(fileConfig);
        this.regex = regex;
    }

    @Override
    public void addPhrase(Phrase phrase) {
        var dictionary = getDictionary();

        try {
            if(dictionary.isEmpty()){
                throw new EmptyDictionaryException();
            }
        } catch (EmptyDictionaryException exception){
            System.out.println(exception.getMessage());
        }
            for(Phrase dictionaryPhrase : dictionary){
                try {
                    if(phrase.equals(dictionaryPhrase)){
                        throw new ExistWordDictionaryException(phrase.getWord(), dictionaryPhrase.getTranslate());
                    }
                } catch (ExistWordDictionaryException exception){
                    System.out.println(exception.getMessage());
                }
            }
    }

    @Override
    public Set<Phrase> deletePhrase(String word) {

        var dictionary = getDictionary();

        try {
            if(dictionary.isEmpty()){
                throw new EmptyDictionaryException();
            }
        } catch (EmptyDictionaryException exception){
            System.out.println(exception.getMessage());
        }

        for(Phrase dictionaryPhrase : dictionary){
            try {
                if(word.equals(dictionaryPhrase.getWord())){
                   dictionary.remove(dictionaryPhrase);
                } else {
                    throw new NotFoundWordDictionaryException(word);
                }
            } catch (NotFoundWordDictionaryException exception){
                System.out.println(exception.getMessage());
            }
        }

        return dictionary;
    }

    @Override
    public void editPhrase(String word) {

    }


    @Override
    public Set<Phrase> getDictionary() {
        Set<Phrase> dictionary = null;
        try {
            var result = dictionaryDao.read();
            if(!result.isEmpty()) {
                var strings = result.split("\n");

                 dictionary = new HashSet<>();

                for(var string : strings){
                    var pairsWords = string.split("-");
                    dictionary.add(new Phrase(pairsWords[0], pairsWords[1]));
                }
            }
        } catch (FileNotFoundException exception){
            System.out.println(exception.getMessage());
        }
        return dictionary;
    }
}
