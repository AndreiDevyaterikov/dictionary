package util;

public enum DictionaryType {

    ENGLISH("1", "[a-zA-Z]+", "[а-яА-Я]+", "EnglishDictionary", "Supports only english words"),
    NUMBER("2", "[0-9]+", "[а-яА-Я]+","NumberDictionary", "Supports only number words");

    private final String position;
    private final String regexWord;
    private final String regexTranslate;
    private final String title;
    private final String description;

    DictionaryType(String position, String regexWord, String regexTranslate, String title, String description) {
        this.position = position;
        this.regexWord = regexWord;
        this.regexTranslate = regexTranslate;
        this.title = title;
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public String getRegexWord() {
        return regexWord;
    }

    public String getRegexTranslate() {
        return regexTranslate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
