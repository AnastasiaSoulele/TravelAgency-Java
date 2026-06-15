import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class destination extends JDialog {
    private JPanel destination;
    private JTextField textDestinationID;
    private JTextField textDestName;
    private JTextField textDestDescr;
    private JComboBox BoxDestType;
    private JTextField textDestLanguage;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxerLocation;



    public destination(JFrame parent) throws SQLException {
        super(parent);

        setTitle("destination");
        setContentPane(destination);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");


        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT dst_id FROM destination");
        while (resultSet.next()) {

            int Location = resultSet.getInt("dst_id");
            BoxerLocation.addItem(Location);
        }

        BoxerLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);

            }
        });


        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                BoxDestType.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setVisible(true);
                    }
                });


                String query = "INSERT INTO destination (dst_id ,  dst_name , dst_descr , dst_rtype , dst_language , dst_location) " +
                        "VALUES(? , ? , ? , ?, ? , ?)";

                PreparedStatement insertStatementDest = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                insertStatementDest.setObject(1, null);
                insertStatementDest.setString(2, textDestName.getText());
                insertStatementDest.setString(3, textDestDescr.getText());
                insertStatementDest.setString(4, (String) BoxDestType.getSelectedItem());
                insertStatementDest.setString(5, textDestLanguage.getText());
                insertStatementDest.setInt(6, (Integer) BoxerLocation.getSelectedItem());


                insertStatementDest.executeUpdate();


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);

    }

}

