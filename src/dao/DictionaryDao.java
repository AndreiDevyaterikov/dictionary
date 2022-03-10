package dao;

import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.NotFoundWordDictionaryException;
import model.Phrase;

import java.util.Map;
import java.util.Optional;


public interface DictionaryDao {
    Phrase create(Phrase phrase) throws ExistWordDictionaryException;
    Map<String, String> read() ;
    Phrase update (Phrase phrase) throws EmptyDictionaryException, NotFoundWordDictionaryException;
    Optional<Phrase> delete(String word) throws EmptyDictionaryException, NotFoundWordDictionaryException;
    Optional<Phrase> findByWord(String word);
}

