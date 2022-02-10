package dao;

import java.io.FileNotFoundException;

public interface DictionaryDao {
    String read() throws FileNotFoundException;
    void write();
}
