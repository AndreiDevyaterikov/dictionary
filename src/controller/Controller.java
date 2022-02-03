package controller;

import exception.DictionaryFormatException;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.NotFoundWordDictionaryException;
import model.DictionaryModel;
import model.Phrase;
import service.DictionaryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Controller {

    private final DictionaryService dictionaryService;

    public Controller(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    public Map<String, String> selectDictionary() throws IOException, DictionaryFormatException, ExistWordDictionaryException, EmptyDictionaryException, NotFoundWordDictionaryException {

        DictionaryModel numberDictionary = new DictionaryModel("NumberDictionary.txt", "[0-9]+");
        DictionaryModel englishDictionary = new DictionaryModel("EnglishDictionary.txt", "[a-zA-Z]+");


        System.out.println("""
                Choose dictionary:\s
                1 - EnglishDictionary\s
                2 - NumberDictionary\s
                3 - Exit""");

        String typeDictionary = new BufferedReader(new InputStreamReader(System.in)).readLine();

        return switch (typeDictionary){
            case "1" -> actionWithDictionary(englishDictionary);
            case "2" -> actionWithDictionary(numberDictionary);
            default -> null;
        };
    }


    private Map<String, String> actionWithDictionary(DictionaryModel dictionary) throws IOException, DictionaryFormatException, ExistWordDictionaryException, EmptyDictionaryException, NotFoundWordDictionaryException {
        System.out.println("""
                Action with dictionary:\s
                1 - Add\s
                2 - Delete\s
                3 - GetDictionary\s
                """);
        String actionWithDictionary = new BufferedReader(new InputStreamReader(System.in)).readLine();

        return switch (actionWithDictionary) {
            case "1" -> addPhrase(dictionary);
            case "2" -> deletePhrase(dictionary);
            case "3" -> getDictionary(dictionary);
            default -> null;
        };
    }

    private Map<String, String> getDictionary(DictionaryModel dictionary) throws IOException {
        return dictionaryService.getDictionary(dictionary.getPathFile());
    }

    private Map<String, String> deletePhrase(DictionaryModel dictionary) throws IOException, EmptyDictionaryException, NotFoundWordDictionaryException {
        System.out.println("Key: ");
        String key = new BufferedReader(new InputStreamReader(System.in)).readLine();

        return dictionaryService.deletePhrase(key, dictionary);
    }

    private Map<String, String> addPhrase(DictionaryModel dictionary) throws IOException, DictionaryFormatException, ExistWordDictionaryException {

        System.out.println("Key: ");
        String key = new BufferedReader(new InputStreamReader(System.in)).readLine();

        System.out.println("Value: ");
        String value = new BufferedReader(new InputStreamReader(System.in)).readLine();

        Phrase phrase = new Phrase();
        phrase.setWord(key);
        phrase.setTranslate(value);

        return dictionaryService.addPhrase(phrase, dictionary);
    }
    
    


}
