import java.io.Serializable;

class Document implements Serializable {
    public String name;
    public String directory;
    Document(String name, String directory){
        this.name = name;
        this.directory = directory;
    }
}