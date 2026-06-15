import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class offers extends JDialog{
    private JPanel offers;
    private JTextField textOfferID;
    private JTextField textStartDate;
    private JTextField textEndDate;
    private JTextField textCostPerPerson;
    private JTextField textDestID;

    private JButton confirmedButton;
    private JComboBox BoxDestID;
    private JLabel Result;

    public offers(JFrame parent) throws SQLException {
        super(parent);

        setTitle("offers");
        setContentPane(offers);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");

        Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT dst_id FROM destination");

        while (resultSet.next()) {
            int DestID= resultSet.getInt("dst_id");
            BoxDestID.addItem(DestID);

        }

        BoxDestID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);

            }
        });


        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    Date startDate = Date.valueOf(textStartDate.getText());
                    Date endDate = Date.valueOf(textEndDate.getText());


                    String query = "INSERT INTO offers ( offer_id ,start_date,end_date, cost_per_person,dest_id ) " +
                            "VALUES( ? , ? , ? , ? , ? )";

                    PreparedStatement insertStatementOffer = connection.prepareStatement(query);

                    insertStatementOffer.setObject(1, null);
                    insertStatementOffer.setString(2, String.valueOf(startDate));
                    insertStatementOffer.setString(3, String.valueOf(endDate));
                    insertStatementOffer.setString(4,textCostPerPerson.getText());
                    insertStatementOffer.setInt(5, (Integer) BoxDestID.getSelectedItem());

                    insertStatementOffer.executeUpdate();


                }catch(SQLException event){
                    event.printStackTrace();
                }


                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);


    }

}
