import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;


public class Client {

    public static void main(String args[]){
        try {
            Socket a = new Socket("localhost",5000);
            Document s = new Document("following:","/Users/rustamquliyev/Desktop/salam");
            ObjectOutputStream out = new ObjectOutputStream(a.getOutputStream());
            out.writeObject(s);

        }catch (IOException e ){
            e.printStackTrace();
        }


    }


}
