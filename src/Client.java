import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;


public class Client {
    String name;
    String path;
    public  Client(String name, String path){
        try {
            this.name = name;
            this.path = path;
            Socket a = new Socket("localhost",5000);
            Document s = new Document(name,path);
            ObjectOutputStream out = new ObjectOutputStream(a.getOutputStream());
            out.writeObject(s);
            DataInputStream dIn = new DataInputStream(a.getInputStream());
            PrintWriter writer = new PrintWriter("client_output.txt");
            int i =0;
            boolean done = false;
            String bt = dIn.readUTF();
            writer.print(bt);
            writer.close();
            JFrame frame = new JFrame("output");
            JPanel x = new JPanel();
            JTextField y = new JTextField(bt);
            x.add(y);
            frame.setContentPane(x);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }catch (IOException e ) {
            e.printStackTrace();
        }

    }


}
