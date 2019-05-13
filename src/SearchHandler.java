import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;

public class SearchHandler extends Thread{
final int maxThreads;
private int  wordCount;
private Socket request;
public int threads =0;
private String name;
private String dir;
public StringBuffer output;
private ExecutorService executor;
private CompletionService completion;
public SearchHandler(int maxThreads, Socket request, ExecutorService exec) throws IOException{
    this.maxThreads = maxThreads;
    wordCount =0;
    output = new StringBuffer();
    executor = exec;
    this.request = request;
    if(request==null)throw new IOException();
    completion = new ExecutorCompletionService(executor);
}

public void incrementWordCount(int word){
    wordCount+=word;
}
public void run(){
    try {
        Thread temp =null;

        ObjectInputStream a = new ObjectInputStream(request.getInputStream());
        Document s = (Document)a.readObject();
        name  = s.getName();
        dir = s.getDirectory();
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles==null || folder ==null){
            System.out.println(wordCount);
            return;
        };
       // ArrayList <Thread> threads = new ArrayList<Thread>();
        for(File x : listOfFiles){
            if(x.isFile()){
                threads++;
            }
        }
        for(File x: listOfFiles){
          if(x.isFile()){
              FileHandler tmp = new FileHandler(this,x,name);

              executor.execute(tmp);
             // threads.add(tmp);
              //completion.submit(tmp,this);

          }
        }


       /* new ThreadCoordinate(this);*/
        synchronized (this){
            this.wait();
        }
       output.append("\n---------------------\nTotal is: "+wordCount+"\n---------------------\n\n");
        System.out.println(output);
       createFile();




    }catch (IOException e){
        e.printStackTrace();
    }catch (ClassNotFoundException e){
        e.printStackTrace();
    }catch (InterruptedException e){
        e.printStackTrace();
    }
}

public void createFile(){


    try {
        DataOutputStream dOut = new DataOutputStream(request.getOutputStream());
        dOut.writeUTF(String.valueOf(output));
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        writer.println(output);

        writer.close();
    }catch (IOException e){
        e.printStackTrace();
    }

}



}
