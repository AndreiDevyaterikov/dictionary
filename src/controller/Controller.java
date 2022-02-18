package controller;

import model.Phrase;
import service.DictionaryServiceImpl;

public interface Controller {
    Phrase addPhrase(DictionaryServiceImpl service);
    void deletePhrase(DictionaryServiceImpl service);
    void getDictionary(DictionaryServiceImpl service);
}
