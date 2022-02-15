package dao;

import model.Phrase;

public interface DictionaryDao {


    void create(Phrase phrase);
    String read() ;
    void update ();
    void delete();
    Phrase findByWord(String word);
}

