import sun.jvm.hotspot.runtime.Threads;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WordServer {
    final static int port = 5000;
    final static int maxThreads = 8;
    private ServerSocket socket = null;
    WordServer(){
      try {

          socket = new ServerSocket(port);
      }catch (IOException e ){
          e.printStackTrace();

      }
    }
    public void Serve(){
        Socket incoming =null;
        while(!socket.isClosed()){
            try {

                incoming= socket.accept();

            }catch (IOException e){
                System.out.println("Failed to establish connection");
                e.printStackTrace();
            }
            while(Thread.activeCount()>=maxThreads);
            new SearchHandler(maxThreads,incoming);
        }


    }

    public static void main(String args[]){
        WordServer a = new WordServer();
        a.Serve();
    }
}



