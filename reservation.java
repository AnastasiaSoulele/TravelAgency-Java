import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class reservation extends JDialog {
    private JPanel reservation;
    private JTextField textResSeatNum;
    private JTextField textResName;
    private JTextField textResLastName;
    private JComboBox BoxIsAdult;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxerResTripId;


    public reservation(JFrame parent) throws SQLException {
        super(parent);

        setTitle("reservation");
        setContentPane(reservation);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");



        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT tr_id FROM trip");

        while (resultSet.next()) {
            int TripId = resultSet.getInt("tr_id");
            BoxerResTripId.addItem(TripId);
        }

        BoxerResTripId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(true);

            }
        });

        BoxIsAdult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });



        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

        try {

            String query = "INSERT INTO reservation ( res_tr_id ,res_seatnum, res_name , res_lname , res_isadult) " +
                    "VALUES( ? , ? , ? , ? ,? )";

            PreparedStatement insertStatementRes = connection.prepareStatement(query);

            insertStatementRes.setInt(1, (Integer) BoxerResTripId.getSelectedItem());
            insertStatementRes.setString(2,textResSeatNum.getText());
            insertStatementRes.setString(3,textResName.getText() );
            insertStatementRes.setString(4,textResLastName.getText());
            insertStatementRes.setString(5, (String) BoxIsAdult.getSelectedItem());


            insertStatementRes.executeUpdate();


        }catch(SQLException event){
            event.printStackTrace();
        }


                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);


    }
}
