import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class branch extends JDialog {
    private JTextField textBrCode;
    private JLabel br_codeLabel;
    private JTextField textBrStreet;
    private JTextField textBrNum;
    private JTextField textBrCity;
    private JPanel branchForm;
    private JButton confirmedButton;
    private JLabel Result;


    public branch(JFrame parent) {
        super(parent);

        setTitle("branch");
        setContentPane(branchForm);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_agency",
                            "root",
                            "@");


                    String query = "INSERT INTO branch (br_code , br_street , br_num , br_city) " +
                            "VALUES(? , ? , ? , ?)";


                    PreparedStatement insertStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                    insertStatement.setObject(1, null);
                    insertStatement.setString(2,textBrStreet.getText());
                    insertStatement.setString(3,textBrNum.getText());
                    insertStatement.setString(4,textBrCity.getText());


                    insertStatement.executeUpdate();


                } catch (SQLException event) {
                    event.printStackTrace();
                }

                Result.setText("The insertion achieved");

            }
        });

        setVisible(true);

    }

}