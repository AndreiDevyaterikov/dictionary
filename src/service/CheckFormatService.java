package service;

public class CheckFormatService {

    public boolean checkLanguageDictionary(String word, String regex) {
        return word.matches(regex);
    }

    public boolean checkTranslateLanguage(String translate, String regex){
        return translate.matches(regex);
    }
}
