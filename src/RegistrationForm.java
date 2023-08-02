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
        String name=Name.getText();
        String email=Email.getText();
        String phone=Phone.getText();
        String address=Address.getText();
        String password=String.valueOf(Password.getPassword());
        String comformPassword=String.valueOf(ComformPassword.getPassword());

        if (name.isEmpty()||email.isEmpty()||phone.isEmpty()||address.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "please enter all field",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!password.equals(comformPassword)){
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                            "Try again",
                             JOptionPane.ERROR_MESSAGE);
            return;

        }
        user=addUserToDatabase(name,email,phone,address,password);
        if(user!=null){
            dispose();
        }else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;
    private User addUserToDatabase(String name, String email, String phone, String address, String password) {
        User user=null;
        return user;
    }

    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
    }
}
