import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public  class TravelAgency extends JDialog  {

        private JLabel userLabel;
        private JTextField userText;
        private JLabel passwordLabel;
        private JPasswordField passwordText;
        private JPanel panel;
        private JButton button;
        private JTextField enjoyTheWorldEnjoyTextField;
        private JFrame frame;

        public static int LoginComplete =0;


        public TravelAgency(JFrame parent) {
            super(parent);

            setTitle("Travel Agency Account");
            setContentPane(panel);
            setSize(650, 500);
            setModal(true);
            setLocationRelativeTo(parent);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);


            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        Connection connection = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/travel_agency",
                                "root",
                                "@");

                        String username = userText.getText();
                        String password = String.valueOf(passwordText.getPassword());

                        String sql = "SELECT * FROM it WHERE username=? AND password=? ";
                        PreparedStatement pst = connection.prepareStatement(sql);
                        connection.prepareStatement(sql);
                                   pst.setString(1,userText.getText());
                                   pst.setString(2,String.valueOf(passwordText.getPassword()));
                                   ResultSet rs= pst.executeQuery();


                        if (username.isEmpty() || password.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter all the fields",
                                    "Try again",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null,
                                    "Login succesful",
                                    "Enjoy your journay with Travel Booth",
                                    JOptionPane.INFORMATION_MESSAGE);
                            LoginComplete = 1;
                            return;


                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "The password and the username is not confirmed",
                                    "Try again",
                                    JOptionPane.ERROR_MESSAGE);
                            return;

                        }


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            setVisible(true);
        }

}








