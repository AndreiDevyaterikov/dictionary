package service;

import config.FileConfig;

public class NumberDictionaryService extends DictionaryServiceImpl{

    public NumberDictionaryService() {
        super(new FileConfig("NumberDictionary"), "[0-9]+");
    }

}
