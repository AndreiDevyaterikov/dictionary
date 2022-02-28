package dao;

import model.Phrase;

import java.util.Map;


public interface DictionaryDao {
    Phrase create(Phrase phrase);
    Map<String, String> read() ;
    Phrase update (Phrase phrase);
    Phrase delete(String word);
    Phrase findByWord(String word);
}

