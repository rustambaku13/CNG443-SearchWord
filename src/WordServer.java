

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WordServer {
    final static int port = 5000;
    final private int maxThreads;
    private ServerSocket socket = null;
    private ExecutorService executorService;
    WordServer(int max){
      try {
           executorService = Executors.newFixedThreadPool(max);
          socket = new ServerSocket(port);
      }catch (IOException e ){
          e.printStackTrace();

      }
      maxThreads = max;
    }
    public void Serve(){
        Socket incoming =null;
        while(!socket.isClosed()){
            try {

                incoming= socket.accept();
                
                executorService.execute(new SearchHandler(maxThreads,incoming,executorService));
                //new SearchHandler(maxThreads,incoming,executorService).start();
            }catch (IOException e){
                System.out.println("Failed to establish connection");
                e.printStackTrace();
            }

        }


    }

    public static void main(String args[]){
        new ClientForm().initialize();
        WordServer a = new WordServer(10);
        a.Serve();
    }
}



