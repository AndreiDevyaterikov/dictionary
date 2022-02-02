package model;

import strategy.EnglishStrategy;

public class EnglishDictionary extends BaseDictionary{
    public EnglishDictionary(String pathFile){
        super(pathFile);
        this.baseStrategy = new EnglishStrategy();
    }
}
