package model;

import strategy.NumberStrategy;

public class NumberDictionary extends BaseDictionary{
    public NumberDictionary(String pathFile){
        super(pathFile);
        this.baseStrategy = new NumberStrategy();
    }
}
