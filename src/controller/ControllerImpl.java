package controller;

import model.Phrase;
import service.DictionaryServiceImpl;
import service.EnglishDictionaryService;
import service.NumberDictionaryService;
import java.util.Scanner;

public class ControllerImpl implements Controller {

    public void control(){
        System.out.println("""
                Select dictionary\s
                1 - English\s
                2 - Number""");

        String typeDictionary = new Scanner(System.in).nextLine();

            switch (typeDictionary) {
                case "1" -> actionWithDictionary(new EnglishDictionaryService());
                case "2" -> actionWithDictionary(new NumberDictionaryService());
            }
    }

    private void actionWithDictionary(DictionaryServiceImpl service)  {
        System.out.println("""
                Select action\s
                1 - Add phrase\s
                2 - Delete phrase\s
                3 - Get dictionary""");

        String action = new Scanner(System.in).nextLine();

        switch (action) {
            case "1" -> addPhrase(service);
            case "2" -> deletePhrase(service);
            case "3" -> getDictionary(service);
        }
    }

    @Override
    public void addPhrase(DictionaryServiceImpl service) {

        System.out.print("Print word:");
        String word = new Scanner(System.in).nextLine();

        System.out.print("Print translate:");
        String translate = new Scanner(System.in).nextLine();

        service.addPhrase(new Phrase(word, translate));

    }

    @Override
    public void deletePhrase(DictionaryServiceImpl service) {

        System.out.print("Print word:");
        String word = new Scanner(System.in).nextLine();
        service.deletePhrase(word);
    }

    @Override
    public void getDictionary(DictionaryServiceImpl service) {
        service.printDictionary();
    }
}
