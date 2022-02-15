package dao;

import config.FileConfig;
import model.Phrase;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileDictionaryDao implements DictionaryDao{

    static Logger LOGGER = Logger.getLogger(FileDictionaryDao.class.getName());

    private final FileConfig fileConfig;

    public FileDictionaryDao(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }


    @Override
    public String read() {
        var file = getFile();

        StringBuilder result = new StringBuilder();

        try {
            var scanner = new Scanner(file);

            while((scanner.hasNextLine())){

                String line = scanner.nextLine();
                if(!result.isEmpty()){
                    result.append("\n");
                }
                result.append(line);
            }
        } catch (FileNotFoundException exception){
            LOGGER.log(Level.WARNING, "Не найден файл по пути: " + file.getAbsolutePath());
        }
        return result.toString();
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public Phrase findByWord(String word) {
        return new Phrase("", "");

    }

    @Override
    public void create(Phrase phrase) {

        var file = getFile();
        try (FileWriter fileWriter = new FileWriter(file, true)){
            fileWriter.write(phrase.getWord()+ " - " + phrase.getTranslate());
            fileWriter.write("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private File getFile() {
        File file = new File(fileConfig.getPathName());
        try{
            if(file.createNewFile()){
                return file;
            }
        } catch (IOException exception){
            LOGGER.log(Level.WARNING, exception.getMessage());
        }
        return file;
    }

}
