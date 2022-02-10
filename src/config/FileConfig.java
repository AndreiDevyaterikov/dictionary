package config;

public class FileConfig {

    private String pathName;

    public FileConfig(String pathName) {
        this.pathName = pathName;
    }

    public String getPathName() {
        return pathName + ".txt";
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}
