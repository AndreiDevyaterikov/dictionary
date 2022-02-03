package model;

public class DictionaryModel {

    private String pathFile;
    private String pattern;

    public DictionaryModel(String pathFile, String pattern) {
        this.pathFile = pathFile;
        this.pattern = pattern;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
