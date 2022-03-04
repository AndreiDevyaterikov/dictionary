package controller;

import model.Dictionary;
import model.Phrase;
import service.DictionaryCreator;
import util.DictionaryType;

import java.util.Map;

public class Controller {

    private final String SPACE = " ";
    private final String PRINT = "Print";
    private final String WORD = "Word";
    private final String TRANSLATE = "Translate";

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

        String word = enterWordOrTranslate(PRINT + SPACE + WORD);
        String translate = enterWordOrTranslate(PRINT + SPACE + TRANSLATE);
        return dictionary.addPhrase(word, translate);
    }

    public Phrase deletePhrase(Dictionary dictionary){

        String word = enterWordOrTranslate(PRINT + SPACE + WORD);
        return dictionary.deletePhrase(word);
    }

    public Phrase findByWord(Dictionary dictionary){

        String word = enterWordOrTranslate(PRINT + SPACE + WORD);
        return dictionary.findByWord(word);
    }

    public Map<String, String> getDictionary(Dictionary dictionary){
        return dictionary.getDictionary();
    }

    public Phrase editPhrase(Dictionary dictionary){

        String word = enterWordOrTranslate(PRINT + SPACE + WORD);
        String newTranslate = enterWordOrTranslate(PRINT + SPACE + TRANSLATE);

        return dictionary.editPhrase(word, newTranslate);
    }

    private String enterWordOrTranslate(String message){
        System.out.println(message);
        return System.console().readLine();
    }
}
