package service;

import config.FileConfig;

public class EnglishDictionaryService extends DictionaryServiceImpl{

    public EnglishDictionaryService() {
        super(new FileConfig("EnglishDictionary"), "[a-zA-Z]+");
    }
}
