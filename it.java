import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class it  extends JDialog {
    private JLabel itATtext;
    private JComboBox BoxeritAT;
    private JButton confirmedButton;
    private JTextField UsernameText;
    private JPasswordField passwordText;
    private JTextField StartDateText;

    private JLabel Result;
    private JPanel it;
    private JTextField EndDateText;


    public it(JFrame parent) throws SQLException {
        super(parent);

        setTitle("IT manager");
        setContentPane(it);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT wrk_AT FROM worker");

        while (resultSet.next()) {
            String WorkerAT = resultSet.getString("wrk_AT");
            BoxeritAT.addItem(WorkerAT);
        }


        BoxeritAT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });



        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {


                    Date startDate = Date.valueOf(StartDateText.getText());
                    Date endDate = Date.valueOf(EndDateText.getText());

                    String query = "INSERT INTO it (it_AT, username, password, start_date, end_date) " +
                            "VALUES(? , ? , ?, ? , ?)";


                    PreparedStatement insertStatementIT = connection.prepareStatement(query);
                    insertStatementIT.setString(1, (String) BoxeritAT.getSelectedItem());
                    insertStatementIT.setString(2, UsernameText.getText());
                    insertStatementIT.setString(3, passwordText.getText());
                    insertStatementIT.setString(4, String.valueOf(startDate));
                    insertStatementIT.setString(5, String.valueOf(endDate));


                    insertStatementIT.executeUpdate();


                }catch(SQLException event){
                    event.printStackTrace();
                }


                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);

    }
}

