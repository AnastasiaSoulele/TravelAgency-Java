import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class manages extends JDialog {
    private JPanel manages;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxManagerBrCode;
    private JComboBox BoxerMngAT;

    public manages(JFrame parent) throws SQLException {
        super(parent);

        setTitle("manages");
        setContentPane(manages);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");

        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery("SELECT wrk_AT FROM worker");

        while (resultSet1.next()) {
            String WorkerAT = resultSet1.getString("wrk_AT");
            BoxerMngAT.addItem(WorkerAT);
        }


        BoxerMngAT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(true);

            }
        });

        Statement statement2= connection.createStatement();
        ResultSet resultSet2=statement2.executeQuery("SELECT br_code FROM branch");
        while (resultSet2.next()) {

            int WorkerBrCode= resultSet2.getInt("br_code");
            BoxManagerBrCode.addItem(WorkerBrCode);
        }


        BoxManagerBrCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(true);

            }
        });


        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

        try {

            String query = "INSERT INTO manages ( mng_adm_AT ,mng_br_code ) " +
                    "VALUES( ? , ?  )";

            PreparedStatement insertStatementManages = connection.prepareStatement(query);

            insertStatementManages.setString(1, (String) BoxerMngAT.getSelectedItem());
            insertStatementManages.setInt(2, (Integer) BoxManagerBrCode.getSelectedItem());



            insertStatementManages.executeUpdate();

        }catch(SQLException event){
            event.printStackTrace();
        }


        Result.setText("The insertion achieved");

        }

    });

    setVisible(true);

    }
}
