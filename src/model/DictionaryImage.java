package model;

import java.util.Map;

public class DictionaryImage {

    private Phrase phrase;
    private Integer rowNumber;
    private Map<String, String> dictionary;

    public DictionaryImage(Phrase phrase, Integer rowNumber, Map<String, String> dictionary) {
        this.phrase = phrase;
        this.rowNumber = rowNumber;
        this.dictionary = dictionary;
    }

    public DictionaryImage() {

    }

    public Phrase getPhrase() {
        return phrase;
    }

    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }
}
