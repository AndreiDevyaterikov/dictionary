package service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DictionaryFileStorage {

    private File getFile(String pathFile)  {

        File file = new File(pathFile);
        try {
            if(!file.exists()){
                var created = file.createNewFile();
                if(created){
                    System.out.println("Created new file - " + pathFile);
                }
                return file;
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return file;
    }

    public Map<String, String> readFromFile(String pathFile) throws IOException {

        File file = getFile(pathFile);

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

    public Map<String, String> writeToFile(Map<String, String> dictionary, String pathFile) throws IOException {

        FileWriter writer = new FileWriter(getFile(pathFile));
        for(Map.Entry<String, String> entry : dictionary.entrySet()){
            writer.write(entry.getKey() + " - " + entry.getValue());
        }
        writer.close();
        return dictionary;
    }
}
