package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileService {

    private File checkExistFile(String fileName) throws IOException{

        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
            return file;
        }
        return file;
    }

    public Map<String, String> readFromFile(String pathFile) throws IOException {

        File file = checkExistFile(pathFile);

        Map<String, String> dictionary = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while((line = br.readLine()) != null){
            var pairWords = line.split("-");
            dictionary.put(pairWords[0], pairWords[1]);
        }
        br.close();

        return dictionary;
    }

}
