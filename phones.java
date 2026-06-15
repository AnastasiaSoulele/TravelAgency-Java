import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class phones extends JDialog{
    private JPanel phones;
    private JTextField textPhoneNumber;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxerPhones;

    public phones(JFrame parent) throws SQLException {
        super(parent);

        setTitle("phones");
        setContentPane(phones);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");


        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT br_code FROM branch");

        while (resultSet.next()) {
            int PhoneBrCode = resultSet.getInt("br_code");
            BoxerPhones.addItem(PhoneBrCode);
        }


        BoxerPhones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(true);
            }
        });


        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

        try {

            String queryPhones = "INSERT INTO phones(ph_br_code, ph_number ) " +
                    "VALUES( ? , ? )";

            PreparedStatement insertStatementPhones = connection.prepareStatement(queryPhones);
            insertStatementPhones.setInt(1, (Integer) BoxerPhones.getSelectedItem());
            insertStatementPhones.setString(2, textPhoneNumber.getText());


            insertStatementPhones.executeUpdate();

        }catch(SQLException event){
            event.printStackTrace();
        }

                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);


    }
}