import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
           setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
        final String DB_URL="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME ="Manipulus";
        final String PASSWORD="manipulus";
        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt=conn.createStatement();
            String sql="INSERT INTO users(name,email,phone,address,password)"+
                    "VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,phone);
            preparedStatement.setString(4,address);
            preparedStatement.setString(5,password);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows>0){
                user=new User();
                user.name=name;
                user.email=email;
                user.phone=phone;
                user.address=address;
                user.password=password;
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        RegistrationForm myForm = new RegistrationForm(null);
        User user= myForm.user;
        if(user!=null){
            System.out.println("Successfull registration of: "+user.name);
        }else{
            System.out.println("Registration cancled");
        }
    }
}
