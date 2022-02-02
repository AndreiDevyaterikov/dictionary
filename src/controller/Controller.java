package controller;

import exception.DictionaryFormatException;
import exception.NotExistWordException;
import exception.WordExitException;
import model.BaseDictionary;
import model.EnglishDictionary;
import model.NumberDictionary;
import service.FileService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Controller {

    public void control() throws IOException, DictionaryFormatException, WordExitException, NotExistWordException {

        BaseDictionary engDictionary = new EnglishDictionary("EnglishDictionary.txt");
        BaseDictionary numbDictionary = new NumberDictionary("NumberDictionary.txt");


        System.out.println("""
                Choose dictionary:\s
                1 - EnglishDictionary\s
                2 - NumberDictionary\s
                """);

        String typeDictionary = new BufferedReader(new InputStreamReader(System.in)).readLine();
        while (true){
            switch (typeDictionary){
                case "1":{
                    actionWithDictionary(engDictionary);
                }
                case "2":{
                    actionWithDictionary(numbDictionary);
                }
                default: break;
            }
        }

    }

    private void actionWithDictionary(BaseDictionary baseDictionary) throws IOException, DictionaryFormatException, WordExitException, NotExistWordException {
        System.out.println("""
                Action with dictionary:\s
                1 - Add\s
                2 - Delete\s
                3 - GetDictionary\s
                """);
        String actionWithDictionary = new BufferedReader(new InputStreamReader(System.in)).readLine();
        switch (actionWithDictionary){
            case "1": add(baseDictionary);
            case "2": delete(baseDictionary);
            case "3" :getDictionary(baseDictionary);
        }
    }

    public Map<String, String> add(BaseDictionary dictionary) throws IOException, DictionaryFormatException, WordExitException {
        System.out.println("Key: ");
        String key = new BufferedReader(new InputStreamReader(System.in)).readLine();

        System.out.println("Value: ");
        String value = new BufferedReader(new InputStreamReader(System.in)).readLine();

         return dictionary.add(key, value);
    }

    public void delete(BaseDictionary dictionary) throws IOException, NotExistWordException {

        System.out.println("Key: ");
        String key = new BufferedReader(new InputStreamReader(System.in)).readLine();

        dictionary.delete(key);
    }

    public Map<String, String> getDictionary(BaseDictionary dictionary) throws IOException {

        var x = dictionary.getDictionary();

        FileService fileService = new FileService();
        return fileService.readFromFile(dictionary.getPathFile());
    }
}
