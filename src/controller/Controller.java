package controller;

import model.Dictionary;
import model.ResponseMessage;
import service.DictionaryCreator;
import util.DictionaryMethods;

import java.util.Scanner;

public class Controller {

    private final String SPLITTER = " - ";
    private final String SPACE = " ";
    private final String PRINT_WORD = "Print word";
    private final String PRINT_TRANSLATE = "Print translate";
    private final String SELECT_DICTIONARY = "Select dictionary";
    private final String SELECT_ACTION = "Select action";
    private final String SELECTED = "Selected";

    DictionaryCreator dictionaryCreator;

    public Controller(DictionaryCreator dictionaryCreator) {
        this.dictionaryCreator = dictionaryCreator;
    }

    public void selectDictionary(){

        System.out.println(SELECT_DICTIONARY);
        for(var dictionary : dictionaryCreator.getDictionaries()){
            System.out.println(dictionary.getDictionaryType().getPosition() + SPLITTER + dictionary.getDictionaryType().getTitle());
        }

//        String typeDictionary = System.console().readLine();
        String typeDictionary = new Scanner(System.in).nextLine();
        for(var dictionary : dictionaryCreator.getDictionaries()){
            var dictionaryPosition = dictionary.getDictionaryType().getPosition();
            if(typeDictionary.equals(dictionaryPosition)){
                selectAction(dictionary);
            }
        }
    }

    public void selectAction(Dictionary dictionary){

        System.out.println(SELECTED + SPACE + dictionary.getDictionaryType().getTitle());
        System.out.println(SELECT_ACTION);
        for(var dictionaryMethod : DictionaryMethods.values()){
            System.out.println(dictionaryMethod.getMethodPosition() + SPLITTER + dictionaryMethod.getMethodTitle());
        }

//        String action = System.console().readLine();
        String action = new Scanner(System.in).nextLine();
        for(var dictionaryMethod : DictionaryMethods.values()){
            if(action.equals(dictionaryMethod.getMethodPosition())){
                switch (dictionaryMethod){
//                    case ADD -> addPhrase(dictionary);
//                    case DELETE -> deletePhrase(dictionary);
//                    case FIND_BY_WORD -> findByWord(dictionary);
//                    case UPDATE -> editPhrase(dictionary);
//                    case GET_DICTIONARY -> getDictionary(dictionary);

                    case ADD -> System.out.println(addPhrase(dictionary));
                    case DELETE -> System.out.println(deletePhrase(dictionary));
                    case FIND_BY_WORD -> System.out.println(findByWord(dictionary));
                    case UPDATE -> System.out.println(editPhrase(dictionary));
                    case GET_DICTIONARY -> System.out.println(getDictionary(dictionary));
                }
            }
        }
    }


    public ResponseMessage addPhrase(Dictionary dictionary){

        String word = enterString(PRINT_WORD);
        String translate = enterString(PRINT_TRANSLATE);
        return dictionary.addPhrase(word, translate);
    }

    public ResponseMessage deletePhrase(Dictionary dictionary){
        String word = enterString(PRINT_WORD);
        return dictionary.deletePhrase(word);
    }

    public ResponseMessage findByWord(Dictionary dictionary){
        String word = enterString(PRINT_WORD);
        return dictionary.findByWord(word);
    }

    public ResponseMessage getDictionary(Dictionary dictionary){
        return dictionary.getDictionary();
    }

    public ResponseMessage editPhrase(Dictionary dictionary){
        String word = enterString(PRINT_WORD);
        String newTranslate = enterString(PRINT_TRANSLATE);
        return dictionary.editPhrase(word, newTranslate);
    }

    private String enterString(String message){
        System.out.println(message);
//        return System.console().readLine();
        return new Scanner(System.in).nextLine();
    }
}
