import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientForm {
    private JPanel panel1;
    private JTextField folderNameTextField;
    private JTextField wordTextField;
    private JButton submitButton;

    public ClientForm() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = folderNameTextField.getText();
                String name = wordTextField.getText();
               new Client(name,path);
            }
        });
    }

    public void initialize(){
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new ClientForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


