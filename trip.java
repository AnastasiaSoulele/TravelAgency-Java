import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class trip extends JDialog {
    private JPanel trip;
    private JTextField textTripID;
    private JTextField textTripDeparture;
    private JTextField textTripReturn;
    private JTextField textTripMaxSeats;
    private JTextField textTripCost;
    private JLabel Result;
    private JButton confirmedButton;
    private JComboBox BoxTripBrCode;
    private JComboBox BoxGuideAT;
    private JComboBox BoxDriverAT;


    public trip(JFrame parent) throws SQLException {
        super(parent);

        setTitle("trip");
        setContentPane(trip);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");


        Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT br_code FROM branch");
        while (resultSet.next()) {

            int Tripbr= resultSet.getInt("br_code");
            BoxTripBrCode.addItem(Tripbr);

        }

        BoxTripBrCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(true);
            }
        });


        Statement statement1=connection.createStatement();
        ResultSet resultSet1=statement1.executeQuery("SELECT gui_AT FROM guide");
        while (resultSet1.next()) {

            String guideAT = resultSet1.getString("gui_AT");
            BoxGuideAT.addItem(guideAT);
        }

        BoxGuideAT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);

            }
        });

        Statement statement2 = connection.createStatement();
        ResultSet resultSet2 = statement2.executeQuery("SELECT drv_AT FROM driver");
        while (resultSet2.next()) {

            String WorkerAT = resultSet2.getString("drv_AT");
            BoxDriverAT.addItem(WorkerAT);
        }

        BoxDriverAT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(true);
            }
        });


        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

        try {
            Date DepartureDate = Date.valueOf(textTripDeparture.getText());
            Date ReturnDate = Date.valueOf(textTripReturn.getText());



            String query = "INSERT INTO trip (tr_id , tr_departure , tr_return, tr_maxseats , tr_cost , tr_br_code , tr_gui_AT ,tr_drv_AT) " +
                    "VALUES( ? , ? , ? , ? , ? , ? , ? , ?)";

            PreparedStatement insertStatementTrip = connection.prepareStatement(query);
            insertStatementTrip.setObject(1,null);
            insertStatementTrip.setString(2, String.valueOf(DepartureDate));
            insertStatementTrip.setString(3, String.valueOf(ReturnDate));
            insertStatementTrip.setString(4, textTripMaxSeats.getText());
            insertStatementTrip.setString(5,textTripCost.getText());
            insertStatementTrip.setInt(6, (Integer) BoxTripBrCode.getSelectedItem());
            insertStatementTrip.setString(7, (String) BoxGuideAT.getSelectedItem());
            insertStatementTrip.setString(8, (String) BoxDriverAT.getSelectedItem());



            insertStatementTrip.executeUpdate();

        }catch(SQLException event){
            event.printStackTrace();
        }


                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);



    }
}

