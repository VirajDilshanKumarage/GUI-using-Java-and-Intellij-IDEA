import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JDialog{
    private JTextField Email;
    private JTextField Name;
    private JTextField Phone;
    private JTextField Address;
    private JPasswordField Password;
    private JPasswordField ComformPassword;
    private JButton Register;
    private JButton Cancle;
    private JPanel RegisterPanel;

    public RegistrationForm(JFrame parent) {
           super(parent);
           setTitle("Create a new account");
           setContentPane(RegisterPanel);
           setMinimumSize(new Dimension(500,474));
           setModal(true);
           setLocationRelativeTo(parent);

        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        Cancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerUser() {
    }

    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
    }
}
