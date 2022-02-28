package service;

import model.Phrase;
import java.util.Map;

public interface DictionaryService {
    Phrase addPhrase(Phrase phrase);
    Map<String, String> getResult();
    Phrase editPhrase(Phrase phrase);
    Phrase deletePhrase(String word);
    Phrase findByWord(String word);
}
