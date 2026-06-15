import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class event extends JDialog {
    private JPanel event;
    private JTextField textEventStart;
    private JTextField textEventEnd;
    private JTextField textEventDescr;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxEventTripID;


    public event(JFrame parent) throws SQLException {
        super(parent);

        setTitle("event");
        setContentPane(event);
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
            BoxEventTripID.addItem(TripID);

        }

        BoxEventTripID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);

            }
        });


        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

        try {

            Date startDate = Date.valueOf(textEventStart.getText());
            Date endDate = Date.valueOf(textEventEnd.getText());

            String queryEvent = "INSERT INTO event (ev_tr_id,ev_start,ev_end ,ev_descr) " +
                    "VALUES(? , ? , ? , ?)";

            PreparedStatement insertStatementEvent = connection.prepareStatement(queryEvent);


            insertStatementEvent.setInt(1, (Integer) BoxEventTripID.getSelectedItem());
            insertStatementEvent.setString(2, String.valueOf(startDate));
            insertStatementEvent.setString(3, String.valueOf(endDate));
            insertStatementEvent.setString(4, textEventDescr.getText());


            insertStatementEvent.executeUpdate();



        }catch(SQLException event){
            event.printStackTrace();
        }


                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);
    }


}


