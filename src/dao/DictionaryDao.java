package dao;

import model.Phrase;

import java.io.FileNotFoundException;

public interface DictionaryDao {
    String read() throws FileNotFoundException;
    void write(Phrase phrase);
}
