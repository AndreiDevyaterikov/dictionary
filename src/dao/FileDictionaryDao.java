package dao;

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

    private final DictionaryType dictionaryType;

    private final String FILE_EXTENSION = ".txt";
    private final String SPLITTER = " - ";
    private final String ARROW = " -> ";
    private final String TRANSLATE = "Translate of word";
    private final String SPACE = " ";
    private final String UPDATED = "updated:";
    private final String DELETED = "Deleted phrase";
    private final String FIND = "Find phrase";
    private final String ADDED = "Has been added";
    private final String PHRASE = "Phrase";

    public FileDictionaryDao(DictionaryType dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    @Override
    public Phrase update(Phrase newPhrase) throws EmptyDictionaryException, NotFoundWordDictionaryException {

        var existPhrase = findByWord(newPhrase.getWord());

        if(existPhrase.isPresent()){
            var dictionary = read();
            if(dictionary.isEmpty()){
                throw new EmptyDictionaryException();
            }
            var oldPhrase = existPhrase.get();
            dictionary.replace(oldPhrase.getWord(), oldPhrase.getTranslate(), newPhrase.getTranslate());
            LOGGER.log(Level.INFO, TRANSLATE + SPACE + oldPhrase.getWord() + UPDATED + SPACE + oldPhrase.getTranslate() + ARROW + newPhrase.getTranslate());
            fileWriter(dictionary);
            return newPhrase;

        } else {
            throw new NotFoundWordDictionaryException(newPhrase.getWord());
        }
    }



    @Override
    public Optional<Phrase> delete(String word) throws EmptyDictionaryException, NotFoundWordDictionaryException{

       var existPhrase = findByWord(word);

        if(existPhrase.isPresent()){
            var phrase = existPhrase.get();
            var dictionary = read();
            if(dictionary.isEmpty()){
                throw new EmptyDictionaryException();
            }

            dictionary.remove(phrase.getWord(), phrase.getTranslate());
            LOGGER.log(Level.INFO, DELETED + SPACE + phrase);

            if(dictionary.isEmpty()){
                fileWriter(new HashMap<>());
            } else {
                fileWriter(dictionary);
            }
        } else {
            throw new NotFoundWordDictionaryException(word);
        }
        return existPhrase;
    }


    @Override
    public Optional<Phrase> findByWord(String word) {
        Phrase phrase = new Phrase();
        var dictionary = read();
        if(!dictionary.isEmpty()){
            for(Map.Entry<String, String> entry : dictionary.entrySet()){
                if(entry.getKey().equals(word)){
                    phrase.setWord(entry.getKey());
                    phrase.setTranslate(entry.getValue());
                    LOGGER.log(Level.INFO, FIND + SPLITTER  + phrase);
                    return Optional.of(phrase);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Phrase create(Phrase newPhrase) throws ExistWordDictionaryException{

        var existPhrase = findByWord(newPhrase.getWord());
        if(existPhrase.isEmpty()){
            Map<String, String> dictionary = new HashMap<>();
            dictionary.put(newPhrase.getWord(), newPhrase.getTranslate());
            fileWriter(dictionary);
            LOGGER.log(Level.INFO, PHRASE + SPLITTER + newPhrase.getWord() + SPLITTER + newPhrase.getTranslate() + SPACE + ADDED);
            return newPhrase;
        } else {
            var phrase = existPhrase.get();
            throw new ExistWordDictionaryException(phrase.getWord(), phrase.getTranslate());
        }
    }

    @Override
    public Map<String, String> read() {

        Map<String, String> dictionary = new HashMap<>();

        try {
            var file = getFile();

            var scanner = new Scanner(file);

            while((scanner.hasNextLine())){

                String line = scanner.nextLine();

                var pairWords = line.split(SPLITTER);
                dictionary.put(pairWords[0], pairWords[1]);

            }
            scanner.close();
        } catch (IOException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return dictionary;
    }

    private File getFile() throws IOException {
        File file = new File(dictionaryType.getTitle() + FILE_EXTENSION);
        if(file.createNewFile()){
            return file;
        }
        return file;
    }

    private void fileWriter(Map<String, String> dictionary){
        try (FileWriter fileWriter = new FileWriter(getFile(), true)) {
            if(!dictionary.isEmpty()){
                for(Map.Entry<String, String> entry : dictionary.entrySet()){
                    fileWriter.write(entry.getKey() + SPLITTER + entry.getValue());
                    fileWriter.write("\n");
                }
            }
            fileWriter.write("");
        } catch (IOException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
    }

}
