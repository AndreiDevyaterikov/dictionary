package service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    private String readFile(String pathFile){

        var file = getFile(pathFile);

        StringBuilder body = new StringBuilder();

        try(Scanner scanner = new Scanner(file)) {

            while((scanner.hasNextLine())){
                String line = scanner.nextLine();
                if(!body.isEmpty()){
                    body.append("\n");
                }
                body.append(line);
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return body.toString();
    }


    public Map<String, String> parseBody(String pathFile){

        var body = readFile(pathFile);
        var strings = body.split("\n");

        Map<String, String> dictionary = new HashMap<>();

        for(var string : strings){
           var pairsWords = string.split("-");
           dictionary.put(pairsWords[0], pairsWords[1]);
        }
        return dictionary;
    }

    public Map<String, String> writeToFile(Map<String, String> dictionary, String pathFile)  {

        try (FileWriter writer = new FileWriter(getFile(pathFile))) {
            for(Map.Entry<String, String> entry : dictionary.entrySet()){
                writer.write(entry.getKey() + " - " + entry.getValue());
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return dictionary;
    }
}
