package controller;

import model.Dictionary;
import service.DictionarySelector;
import util.DictionaryType;

public class Controller {

    DictionarySelector dictionarySelector;

    public Controller(DictionarySelector dictionarySelector) {
        this.dictionarySelector = dictionarySelector;
    }

    public Object selectDictionary(){
        System.out.println("""
                Select dictionary\s
                1 - English\s
                2 - Number""");

        String typeDictionary = System.console().readLine();
        switch (typeDictionary){
            case "1" -> {
                var engDictionary = dictionarySelector.selectDictionary(DictionaryType.ENGLISH);
                return actionWithDictionary(engDictionary);
            }

            case "2" -> {
                var numDictionary = dictionarySelector.selectDictionary(DictionaryType.NUMBER);
                return actionWithDictionary(numDictionary);
            }

            default -> {
                return "Not found selected dictionary";
            }
        }

    }

    public Object actionWithDictionary(Dictionary dictionary){

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
            case "1" -> {
               return addPhrase(dictionary);
            }
            case "2" -> {
               return deletePhrase(dictionary);
            }
            case "3" -> {
                return findByWord(dictionary);
            }
            case "4" -> {
                return getDictionary(dictionary);
            }
            case "5" -> {
                return editPhrase(dictionary);
            }
            default -> {
                return "Not found action";
            }
        }

    }

    public Object addPhrase(Dictionary dictionary){

        System.out.print("Print word: ");
        String word = System.console().readLine();

        System.out.print("Print translate: ");
        String translate = System.console().readLine();

        return dictionary.addPhrase(word, translate);
    }

    public Object deletePhrase(Dictionary dictionary){

        System.out.print("Print word: ");
        String word = System.console().readLine();

        return dictionary.deletePhrase(word);
    }

    public Object findByWord(Dictionary dictionary){

        System.out.print("Print word: ");
        String word = System.console().readLine();

        return dictionary.findByWord(word);
    }

    public Object getDictionary(Dictionary dictionary){
        return dictionary.getDictionary();
    }

    public Object editPhrase(Dictionary dictionary){

        System.out.print("Print word: ");
        String word = System.console().readLine();

        System.out.print("Print new translate: ");
        String newTranslate = System.console().readLine();

        return dictionary.editPhrase(word, newTranslate);
    }
}
