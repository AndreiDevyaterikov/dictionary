package service;

import exception.FormatDictionaryException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckFormatService {

    static Logger LOGGER = Logger.getLogger(CheckFormatService.class.getName());

    public void checkLanguageDictionary(String word, String regex) {
        var correctFormat = word.matches(regex);
        try {
            if(!correctFormat){
                throw new FormatDictionaryException(regex);
            }
        } catch (FormatDictionaryException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
    }
}
