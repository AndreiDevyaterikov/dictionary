package controller;

import service.DictionaryServiceImpl;

public interface Controller {
    void addPhrase(DictionaryServiceImpl service);
    void deletePhrase(DictionaryServiceImpl service);
    void getDictionary(DictionaryServiceImpl service);
}
