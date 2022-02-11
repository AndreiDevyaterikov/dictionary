package service;

import exception.FormatDictionaryException;
import model.Phrase;

import java.util.Set;

public interface DictionaryService {
    void addPhrase(Phrase phrase);
    void deletePhrase(String word);
    void editPhrase(Phrase phrase);
    void printDictionary();
    Set<Phrase> getResult();
    void checkPattern(String word) throws FormatDictionaryException;
}
