package util;

public enum DictionaryType {
    ENGLISH(1, "[a-zA-Z]+", "EnglishDictionary"),
    NUMBER(2, "[0-9]+", "NumberDictionary");

    private final Integer position;
    private final String regex;
    private final String title;

    DictionaryType(Integer position, String regex, String title) {
        this.position = position;
        this.regex = regex;
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public String getRegex() {
        return regex;
    }

    public String getTitle() {
        return title;
    }
}
