package service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckFormatService {

    static Logger LOGGER = Logger.getLogger(CheckFormatService.class.getName());

    public Boolean checkWordTranslateDictionary(String word, String regex) {
        var correctFormat = word.matches(regex);
        if(!correctFormat){
            LOGGER.log(Level.WARNING, "Incorrect format of word");
        }
        return correctFormat;
    }
}
