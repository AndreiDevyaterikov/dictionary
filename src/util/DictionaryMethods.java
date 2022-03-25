package util;

public enum DictionaryMethods {
    ADD("1", "Add phrase"),
    DELETE("2", "Delete phrase"),
    FIND_BY_WORD("3", "Find phrase"),
    UPDATE("4", "Update phrase"),
    GET_DICTIONARY("5", "Get all dictionary");

    private final String methodPosition;

    private final String methodTitle;

    DictionaryMethods(String methodPosition, String methodTitle) {
        this.methodPosition = methodPosition;
        this.methodTitle = methodTitle;
    }

    public String getMethodPosition() {
        return methodPosition;
    }

    public String getMethodTitle() {
        return methodTitle;
    }
}
