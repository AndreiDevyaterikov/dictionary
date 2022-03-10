package dao;

import model.DictionaryImage;
import exception.EmptyDictionaryException;
import exception.ExistWordDictionaryException;
import exception.NotFoundWordDictionaryException;
import model.Phrase;
import util.DictionaryType;
import util.TypesOfWrite;

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

            for(Map.Entry<String, String> entry : dictionary.entrySet()){
                fileWriter(new Phrase(entry.getKey(), entry.getValue()), TypesOfWrite.NEW_OR_UPDATE);
            }

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
                fileWriter(null, TypesOfWrite.DELETE);
            } else {
                for(Map.Entry<String, String> entry : dictionary.entrySet()){
                    fileWriter(new Phrase(entry.getKey(), entry.getValue()), TypesOfWrite.DELETE);
                }
            }
        } else {
            throw new NotFoundWordDictionaryException(word);
        }
        return existPhrase;
    }

    private void fileStorageWriter(DictionaryImage dictionaryImage){
        var dictionary = dictionaryImage.getDictionary();
        var rowNumber = dictionaryImage.getRowNumber();
        var phrase = dictionaryImage.getPhrase();
        int countSkipRows = 0;
        try {
            var file = getFile();
            Scanner scanner = new Scanner(file);
            while((scanner.hasNextLine())){
                countSkipRows++;
                if(countSkipRows == rowNumber){
                    try (FileWriter fileWriter = new FileWriter(file)) {
                        for(Map.Entry<String, String> entry : dictionary.entrySet()){
                            fileWriter.write(entry.getKey() + SPLITTER + entry.getValue());
                            fileWriter.write("\n");
                        }
                    }
                }

            }
            scanner.close();

        } catch (IOException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }

    }

    private DictionaryImage getDictionaryImage(String word){
        Phrase phrase = new Phrase();
        var dictionary = read();
        int rowNumber = 0;
        if(!dictionary.isEmpty()){
            for(Map.Entry<String, String> entry : dictionary.entrySet()){
                if(entry.getKey().equals(word)){
                    phrase.setWord(entry.getKey());
                    phrase.setTranslate(entry.getValue());
                }
                rowNumber++;
                dictionary.remove(entry.getKey(), entry.getValue());

            }
            return new DictionaryImage(phrase, rowNumber, dictionary);
        }
        return null;
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
            fileWriter(newPhrase, TypesOfWrite.NEW_OR_UPDATE);
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

    private void fileWriter(Phrase phrase, TypesOfWrite typesOfWrite){
       try {
           var file = getFile();
           FileWriter fileWriter = new FileWriter(file, true);

           switch (typesOfWrite){
               case NEW_OR_UPDATE -> {
                   fileWriter.write(phrase.getWord()+ SPLITTER + phrase.getTranslate());
                   fileWriter.write("\n");
               }

               case DELETE -> {
                   if(phrase == null){
                       fileWriter.write("");
                   } else {
                       fileWriter.write(phrase.getWord() + SPLITTER + phrase.getTranslate());
                   }
                   fileWriter.write("\n");
               }
           }

           fileWriter.close();

       } catch (IOException exception){
           LOGGER.log(Level.WARNING, exception.getMessage());
       }
    }
}
