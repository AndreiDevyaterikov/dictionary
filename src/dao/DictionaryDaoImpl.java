package dao;

import config.FileConfig;
import model.Phrase;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class DictionaryDaoImpl implements DictionaryDao{

    private final FileConfig fileConfig;

    public DictionaryDaoImpl(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    @Override
    public String read() throws FileNotFoundException {
        var file = get();
        var scanner = new Scanner(file);
        StringBuilder result = new StringBuilder();
        while((scanner.hasNextLine())){

            String line = scanner.nextLine();
            if(!result.isEmpty()){
                result.append("\n");
            }
            result.append(line);
        }
        scanner.close();
        return result.toString();
    }

    @Override
    public void write(Phrase phrase) {

        var file = get();
        try (FileWriter fileWriter = new FileWriter(file, true)){
            fileWriter.write(phrase.getWord()+ " - " + phrase.getTranslate());
            fileWriter.write("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private File get() {
        File file = new File(fileConfig.getPathName());
        try{
            if(file.createNewFile()){
                return file;
            }
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        return file;
    }
}
