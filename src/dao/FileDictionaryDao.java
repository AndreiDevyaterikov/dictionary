package dao;

import util.TextFileExtension;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.NotFoundWordDictionaryException;
import model.Phrase;
import util.DictionaryType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileDictionaryDao implements DictionaryDao{

    static Logger LOGGER = Logger.getLogger(FileDictionaryDao.class.getName());

    private final TextFileExtension textFileExtension;
    private final DictionaryType dictionaryType;

    public FileDictionaryDao(TextFileExtension textFileExtension, DictionaryType dictionaryType) {
        this.textFileExtension = textFileExtension;
        this.dictionaryType = dictionaryType;
    }

    @Override
    public Phrase update(Phrase phrase) {

        var existPhrase = findByWord(phrase.getWord());

        try {
            if(existPhrase != null){
                var dictionary = read();
                if(dictionary.isEmpty()){
                    throw new EmptyDictionaryException();
                }
                dictionary.replace(existPhrase.getWord(), existPhrase.getTranslate(), phrase.getTranslate());
                //TODO Запись в файл

                LOGGER.log(Level.INFO, "Translate of word " + existPhrase.getWord() + " updated: " + existPhrase.getWord() + " -> " + phrase.getTranslate());
            } else {
                throw new NotFoundWordDictionaryException(phrase.getWord());
            }
        } catch (NotFoundWordDictionaryException | EmptyDictionaryException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return phrase;
    }

    @Override
    public Phrase delete(String word) {

        var existPhrase = findByWord(word);

        try {
            if(existPhrase != null){
                var dictionary = read();
                if(dictionary.isEmpty()){
                    throw new EmptyDictionaryException();
                }

                dictionary.remove(existPhrase.getWord(), existPhrase.getTranslate());
                LOGGER.log(Level.INFO, "Deleted phrase - " + existPhrase);
                //TODO Запись в файл


            } else {
                throw new NotFoundWordDictionaryException(word);
            }
        } catch (NotFoundWordDictionaryException | EmptyDictionaryException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return existPhrase;
    }

    @Override
    public Phrase findByWord(String word) {
        Phrase phrase = new Phrase();
        var dictionary = read();
        try {

            if(dictionary.isEmpty()){
                throw new EmptyDictionaryException();
            }

            for(Map.Entry<String, String> entry : dictionary.entrySet()){
                if(entry.getKey().equals(word)){
                    phrase.setWord(entry.getKey());
                    phrase.setTranslate(entry.getValue());
                    LOGGER.log(Level.INFO, "Find phrase - "  + phrase);
                    return phrase;
                }
            }

        } catch (EmptyDictionaryException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return null;
    }

    @Override
    public Phrase create(Phrase phrase) {

        try {
            var existPhrase = findByWord(phrase.getWord());
            if(existPhrase == null){
                var file = getFile();
                FileWriter fileWriter = new FileWriter(file, true);

                fileWriter.write(phrase.getWord()+ " - " + phrase.getTranslate());
                fileWriter.write("\n");
                fileWriter.close();
                LOGGER.log(Level.INFO, "Phrase - " + phrase.getWord() + " - " + phrase.getTranslate() + " has been added");
            } else {
                throw new ExistWordDictionaryException(existPhrase.getWord(), existPhrase.getTranslate());
            }

        } catch (ExistWordDictionaryException | IOException exception) {
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return phrase;
    }

    @Override
    public Map<String, String> read() {

        Map<String, String> dictionary = new HashMap<>();

        try {
            var file = getFile();

            var scanner = new Scanner(file);

            while((scanner.hasNextLine())){

                String line = scanner.nextLine();

                var pairWords = line.split(" - ");
                dictionary.put(pairWords[0], pairWords[1]);

            }
            scanner.close();
        } catch (IOException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return dictionary;
    }

    private File getFile() throws IOException {
        File file = new File(dictionaryType.getName() + textFileExtension.getExtension());
        if(file.createNewFile()){
            return file;
        }
        return file;
    }

}
