package util;

public enum DictionaryType {
    ENGLISH("[a-zA-Z]+", "EnglishDictionary"),
    NUMBER("[0-9]+", "NumberDictionary");

    private final String regex;
    private final String name;

    DictionaryType(String regex, String name) {
        this.regex = regex;
        this.name = name;
    }

    public String getRegex() {
        return regex;
    }

    public String getName() {
        return name;
    }

}
