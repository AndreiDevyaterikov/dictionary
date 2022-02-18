package dao;

import model.Phrase;
import java.util.Set;


public interface DictionaryDao {


    Phrase create(Phrase phrase);
    Set<Phrase> read() ;
    void update (String word);
    void delete(String word);
    Phrase findByWord(String word);
}

