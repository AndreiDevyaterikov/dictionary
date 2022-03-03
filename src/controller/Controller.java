package controller;

import model.Dictionary;
import model.Phrase;
import service.DictionaryCreator;
import util.DictionaryType;

import java.util.Map;

public class Controller {

    DictionaryCreator dictionaryCreator;

    public Controller(DictionaryCreator dictionaryCreator) {
        this.dictionaryCreator = dictionaryCreator;
    }

    public void selectDictionary(){

        String typeDictionary = System.console().readLine();
        switch (typeDictionary){
            case "1" -> {
                var engDictionary = dictionaryCreator.createDictionary(DictionaryType.ENGLISH);
                actionWithDictionary(engDictionary);
            }

            case "2" -> {
                var numDictionary = dictionaryCreator.createDictionary(DictionaryType.NUMBER);
                actionWithDictionary(numDictionary);
            }
        }

    }

    public void actionWithDictionary(Dictionary dictionary){

        System.out.println("Selected " + dictionary.getDictionaryName());
        System.out.println("""
                Select action:\s
                1 - Add\s
                2 - Delete\s
                3 - Find\s
                4 - Get all\s
                5 - Edit""");

        String action = System.console().readLine();
        switch (action){
            case "1" -> addPhrase(dictionary);
            case "2" -> deletePhrase(dictionary);
            case "3" -> findByWord(dictionary);
            case "4" -> getDictionary(dictionary);
            case "5" -> editPhrase(dictionary);
        }

    }

    public Phrase addPhrase(Dictionary dictionary){

        String word = enterWord();
        String translate = enterTranslate();
        return dictionary.addPhrase(word, translate);
    }

    public Phrase deletePhrase(Dictionary dictionary){

        String word = enterWord();
        return dictionary.deletePhrase(word);
    }

    public Phrase findByWord(Dictionary dictionary){

        String word = enterWord();
        return dictionary.findByWord(word);
    }

    public Map<String, String> getDictionary(Dictionary dictionary){
        return dictionary.getDictionary();
    }

    public Phrase editPhrase(Dictionary dictionary){

        String word = enterWord();
        String newTranslate = enterTranslate();

        return dictionary.editPhrase(word, newTranslate);
    }

    private String enterWord(){
        System.out.print("Print word: ");
        return System.console().readLine();
    }

    private String enterTranslate(){
        System.out.print("Print translate: ");
        return System.console().readLine();
    }
}
