package service;

import model.Phrase;

import java.util.Set;

public interface DictionaryService {
    void addPhrase(Phrase phrase);
    Set<Phrase> deletePhrase(String word);
    void editPhrase(String word);
    Set<Phrase> getDictionary();
}
