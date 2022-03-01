package controller;

import dao.FileDictionaryDao;
import model.Phrase;
import service.CheckFormatService;
import service.FileDictionaryService;
import util.DictionaryType;
import util.TextFileExtension;

import java.util.Map;
import java.util.Scanner;


public class FileController{

    public void control(){
        System.out.println("""
                Select type dictionary:\s
                1 - English\s
                2 - Number""");
        String typeDictionary = new Scanner(System.in).nextLine();
        switch (typeDictionary) {
            case "1" -> {
                DictionaryType englishDictionary = DictionaryType.ENGLISH;
                System.out.println(actionWithDictionary(englishDictionary));
            }
            case "2" -> {
                DictionaryType numberDictionary = DictionaryType.NUMBER;
                System.out.println(actionWithDictionary(numberDictionary));
            }

            default -> {
            }
        }
    }

    public Object actionWithDictionary(DictionaryType dictionaryType){
        System.out.println("Selected " + dictionaryType.getName());
        System.out.println("""
                Select action:\s
                1 - Add\s
                2 - Delete\s
                3 - Find\s
                4 - GetAll\s
                5 - Edit""");

        String typeAction = new Scanner(System.in).nextLine();
        switch (typeAction){
            case "1" -> {
                return addPhrase(dictionaryType);
            }
            case "2" -> {
                return deletePhrase(dictionaryType);
            }
            case "3" -> {
                return findPhrase(dictionaryType);
            }
            case "4" -> {
                return getDictionary(dictionaryType);
            }
            case "5" -> {
                return editPhrase(dictionaryType);
            }
            default -> {
                return "Not found action";
            }
        }
    }

    public Phrase addPhrase (DictionaryType dictionaryType){
        FileDictionaryService fileDictionaryService = new FileDictionaryService(dictionaryType,
                new CheckFormatService(),
                new FileDictionaryDao(new TextFileExtension(),
                        dictionaryType));
        System.out.print("Print word: ");
        String word = new Scanner(System.in).nextLine();
        System.out.print("Print translate: ");
        String translate = new Scanner(System.in).nextLine();

        return fileDictionaryService.addPhrase(new Phrase(word, translate));
    }

    public Phrase deletePhrase (DictionaryType dictionaryType){
        FileDictionaryService fileDictionaryService = new FileDictionaryService(dictionaryType,
                new CheckFormatService(),
                new FileDictionaryDao(new TextFileExtension(),
                        dictionaryType));
        System.out.print("Print word: ");
        String word = new Scanner(System.in).nextLine();
        return fileDictionaryService.deletePhrase(word);
    }

    public Phrase findPhrase (DictionaryType dictionaryType){
        FileDictionaryService fileDictionaryService = new FileDictionaryService(dictionaryType,
                new CheckFormatService(),
                new FileDictionaryDao(new TextFileExtension(),
                        dictionaryType));
        System.out.print("Print word: ");
        String word = new Scanner(System.in).nextLine();
        return fileDictionaryService.findByWord(word);
    }

    public Map<String, String> getDictionary(DictionaryType dictionaryType){
        FileDictionaryService fileDictionaryService = new FileDictionaryService(dictionaryType,
                new CheckFormatService(),
                new FileDictionaryDao(new TextFileExtension(),
                        dictionaryType));
        return fileDictionaryService.getResult();
    }

    public Phrase editPhrase(DictionaryType dictionaryType){
        FileDictionaryService fileDictionaryService = new FileDictionaryService(dictionaryType,
                new CheckFormatService(),
                new FileDictionaryDao(new TextFileExtension(),
                        dictionaryType));

        System.out.print("Print word: ");
        String word = new Scanner(System.in).nextLine();
        System.out.print("Print translate: ");
        String translate = new Scanner(System.in).nextLine();

        return fileDictionaryService.editPhrase(new Phrase(word, translate));
    }
}
