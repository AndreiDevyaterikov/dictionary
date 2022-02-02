package strategy;

import exception.DictionaryFormatException;
import exception.NotExistWordException;
import exception.WordExitException;
import model.NumberDictionary;
import service.FileService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnglishStrategy implements BaseStrategy {

    @Override
    public Map<String, String> add(String key, String value) throws WordExitException, DictionaryFormatException, IOException {
        String pattern = "[a-zA-Z]";
        Map<String, String> dictionary = getDictionary();
        if(!key.matches(pattern)){
            throw new DictionaryFormatException("В этот словарь можно доабвлять только слова латинского языка!");
        }
        for (Map.Entry<String, String> entry : dictionary.entrySet()){
            if(key.equals(entry.getKey())){
                throw new WordExitException("Перевод с таким словом уже существует");
            }
            dictionary.put(key,value);
            System.out.println("Запись добавлена");
        }
        return dictionary;
    }

    @Override
    public void delete(String key) throws NotExistWordException, IOException {
        Map<String, String> dictionary = getDictionary();
        for (Map.Entry<String, String> entry : dictionary.entrySet()){
            if(!key.equals(entry.getKey())){
                throw new NotExistWordException("Такого слова не сущесвует: " + key);
            }
            dictionary.remove(key);
            System.out.println("Запись удалена");
        }
    }

    @Override
    public Map<String, String> getDictionary() throws IOException {
        FileService fileService = new FileService();
        return fileService.readFromFile(new NumberDictionary("EnglishDictionary.txt").getPathFile());
    }
}
