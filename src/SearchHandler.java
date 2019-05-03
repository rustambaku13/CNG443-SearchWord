import java.io.*;
import java.net.Socket;

public class SearchHandler extends Thread{
final int maxThreads;
private int  wordCount =0;
private Socket request;
private String name;
public SearchHandler(int maxThreads, Socket request ){
    this.maxThreads = maxThreads;
    this.request = request;
    if(request!=null)this.start();
}

public void incrementWordCount(int word){
    wordCount+=word;
}
public void run(){
    try {
        Thread temp =null;
        ObjectInputStream a = new ObjectInputStream(request.getInputStream());
        Document s = (Document)a.readObject();
        name = s.name;
        File folder = new File(s.directory);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles==null) {System.out.println("no files found");return;}
        for(File x: listOfFiles){
          if(x.isFile()){
              temp = new Thread(new FileHandler(this,x,name));
              temp.start();


          }
        }

        request.close();
        System.out.println(folder.getCanonicalPath()+"Has "+wordCount+" instances of the word:"+name);
        System.out.println("\n\n");

    }catch (IOException e){
        e.printStackTrace();
    }catch (ClassNotFoundException e){
        e.printStackTrace();
    }catch (Exception e){
        e.printStackTrace();
    }
}



}
