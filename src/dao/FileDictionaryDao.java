package dao;

import config.FileConfig;
import exception.EmptyDictionaryException;
import model.Phrase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileDictionaryDao implements DictionaryDao{

    static Logger LOGGER = Logger.getLogger(FileDictionaryDao.class.getName());

    private final FileConfig fileConfig;

    public FileDictionaryDao(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }


    @Override
    public void update(String word) {
        //TODO Реализация изменения
    }

    @Override
    public void delete(String word) {
        //TODO Реализация удаления
    }

    @Override
    public Phrase findByWord(String word) {

        var result = read();

        try {

            if(result.isEmpty()){
                throw new EmptyDictionaryException();
            }
            //TODO Реализация поиска слова

        } catch (EmptyDictionaryException exception){
            LOGGER.log(Level.INFO, exception.getMessage());
        }

        return new Phrase("", "");

    }

    @Override
    public Phrase create(Phrase phrase) {


        try {
            var file = getFile();
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.write(phrase.getWord()+ " - " + phrase.getTranslate());
            fileWriter.write("\n");

            fileWriter.close();


        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        LOGGER.log(Level.INFO, "Phrase - " + phrase.getWord() + " - " + phrase.getTranslate() + " has been added");
        return phrase;
    }

    @Override
    public Set<Phrase> read() {

        Set<Phrase> dictionary = new HashSet<>();

        try {
            var file = getFile();

            var scanner = new Scanner(file);

            while((scanner.hasNextLine())){

                String line = scanner.nextLine();

                var pairWords = line.split(" - ");
                dictionary.add(new Phrase(pairWords[0], pairWords[1]));

            }
            scanner.close();
        } catch (IOException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return dictionary;
    }

    private File getFile() throws IOException {
        File file = new File(fileConfig.getPathName());
        if(file.createNewFile()){
            return file;
        }
        return file;
    }

}
