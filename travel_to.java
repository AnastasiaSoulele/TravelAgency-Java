import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class travel_to extends JDialog{
    private JPanel travel_to;
    private JTextField textToArrival;
    private JTextField textToDeparture;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxToTripID;
    private JComboBox BoxToDstID;

    public travel_to(JFrame parent) throws SQLException {
        super(parent);

        setTitle("travel_to");
        setContentPane(travel_to);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");



        Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT tr_id FROM trip");
        while (resultSet.next()) {

            int TripID= resultSet.getInt("tr_id");
            BoxToTripID.addItem(TripID);

        }
        BoxToTripID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(true);

            }
        });

        Statement statement2= connection.createStatement();
        ResultSet resultSet2=statement2.executeQuery("SELECT dst_id FROM destination");
        while (resultSet2.next()) {

            int DestID= resultSet2.getInt("dst_id");
            BoxToDstID.addItem(DestID);

        }
        BoxToDstID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);

            }
        });


        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

        try {

            Date DepartureDate = Date.valueOf(textToDeparture.getText());
            Date ReturnDate = Date.valueOf(textToArrival.getText());

            String query = "INSERT INTO travel_to ( to_tr_id ,to_dst_id, to_arrival ,to_departure ) " +
                    "VALUES( ? , ? , ? , ?  )";

            PreparedStatement insertStatementToTrip = connection.prepareStatement(query);

            insertStatementToTrip.setInt(1, (Integer) BoxToTripID.getSelectedItem());
            insertStatementToTrip.setInt(2, (Integer) BoxToDstID.getSelectedItem());
            insertStatementToTrip.setString(3, String.valueOf(DepartureDate));
            insertStatementToTrip.setString(4, String.valueOf(ReturnDate));


            insertStatementToTrip.executeUpdate();

        } catch (SQLException event) {
            event.printStackTrace();
        }

                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);


    }
}



