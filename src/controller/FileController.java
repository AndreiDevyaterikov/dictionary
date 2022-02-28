package controller;

import dao.FileDictionaryDao;
import model.Phrase;
import service.CheckFormatService;
import service.FileDictionaryService;
import util.DictionaryType;
import util.TextFileExtension;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Phaser;


public class FileController{

    public void control(){
        System.out.println("""
                Select type dictionary:\s
                1 - English\s
                2 - Number\s
                """);
        String typeDictionary = new Scanner(System.in).nextLine();
        switch (typeDictionary){
            case "1":{
                DictionaryType englishDictionary = DictionaryType.ENGLISH;
                actionWithDictionary(englishDictionary);
            }

            case "2":{
                DictionaryType numberDictionary = DictionaryType.NUMBER;
                actionWithDictionary(numberDictionary);
            }
        }

    }

    public void actionWithDictionary(DictionaryType dictionaryType){
        System.out.println("Selected " + dictionaryType.getName() + " dictionary");
        System.out.println("""
                Select action:\s
                1 - Add\s
                2 - Delete\s
                3 - Find\s
                4 - GetAll\s
                5 - Edit\s
                """);

        String typeAction = new Scanner(System.in).nextLine();
        switch (typeAction){
            case "1" -> addPhrase(dictionaryType);
            case "2" -> deletePhrase(dictionaryType);
            case "3" -> findPhrase(dictionaryType);
            case "4" -> getDictionary(dictionaryType);
            case "5" -> editPhrase(dictionaryType);
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
