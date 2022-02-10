package controller;

import model.Phrase;
import service.EnglishDictionaryService;

public class ControllerImpl implements Controller {

    EnglishDictionaryService englishDictionaryService = new EnglishDictionaryService();


    @Override
    public void addPhrase() {

    }

    @Override
    public void deletePhrase() {

    }

    @Override
    public void editPhrase() {

    }

    @Override
    public void getDictionary() {
        var dictionary = englishDictionaryService.getDictionary();
        for(Phrase phrase : dictionary){
            System.out.println(phrase);
        }
    }
}
