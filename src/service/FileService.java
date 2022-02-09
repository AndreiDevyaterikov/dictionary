package service;

import exception.ExistFileException;
import exception.NotFoundFileException;
import java.io.File;

public class FileService {

    public File getFile(String pathName){
        File file = new File(pathName);
        try {
            if(!file.exists()){
                throw new NotFoundFileException("Not found file with name - " + pathName);
            }
        } catch (NotFoundFileException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }

    public File createFile(String pathName){
        File file = new File(pathName);
        try {
            if(file.exists()){
                throw new ExistFileException("File with " + pathName + " already exists");
            }
        } catch (ExistFileException e){
            System.out.println(e.getMessage());
        }
        return file;
    }
}
