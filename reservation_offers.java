import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class reservation_offers extends JDialog {
    private JPanel reservation_offers;
    private JTextField textResName;
    private JTextField textResLname;
    private JTextField textResOfferID;
    private JTextField textAdvance;

    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxResOfferID;

    public reservation_offers(JFrame parent) throws SQLException {
        super(parent);

        setTitle("reservation_offers");
        setContentPane(reservation_offers);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");


        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT offer_id FROM offers");
        while (resultSet.next()) {

            int OfferID = resultSet.getInt("offer_id");
            BoxResOfferID.addItem(OfferID);

        }

        BoxResOfferID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });



    confirmedButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

         try
        {

        String query = "INSERT INTO reservation_offers ( res_id ,r_name, r_lname , res_offer_id, advance) " +
                "VALUES( ? , ? , ? , ? , ? )";

        PreparedStatement insertStatementResOffer = connection.prepareStatement(query);

        insertStatementResOffer.setObject(1, null);
        insertStatementResOffer.setString(2, textResName.getText());
        insertStatementResOffer.setString(3, textResLname.getText());
        insertStatementResOffer.setInt(4, (Integer) BoxResOfferID.getSelectedItem());
        insertStatementResOffer.setString(5, textAdvance.getText());


        insertStatementResOffer.executeUpdate();


        } catch (SQLException ex) {
             throw new RuntimeException(ex);
         }

            Result.setText("The insertion achieved");

        }

    });

        setVisible(true);


    }
}



