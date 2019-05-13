import java.io.Serializable;

class Document implements Serializable {
    private String name;
    private String directory;
    Document(String name, String directory){
        this.name = name;
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }
}