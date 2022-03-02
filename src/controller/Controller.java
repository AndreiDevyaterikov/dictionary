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
                DictionaryType english = DictionaryType.ENGLISH;
                return actionWithDictionary(english);
            }

            case "2" -> {
                DictionaryType number = DictionaryType.NUMBER;
                return actionWithDictionary(number);
            }

            default -> {
                return "Not found selected dictionary";
            }
        }

    }

    public Object actionWithDictionary(DictionaryType dictionaryType){
        var dictionary = dictionarySelector.selectDictionary(dictionaryType);

        System.out.println("Selected " + dictionaryType.getName());
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
