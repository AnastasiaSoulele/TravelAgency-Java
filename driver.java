import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class driver extends JDialog{
    private JPanel driver;
    private JComboBox BoxDriverLicense;
    private JComboBox BoxDriverRoute;
    private JTextField textDriverExperience;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxerDriverAT;


    public driver(JFrame parent) throws SQLException {
        super(parent);

        setTitle("driver");
        setContentPane(driver);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");

        Statement statement = connection.createStatement();


        BoxDriverLicense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);

            }
        });

        BoxDriverRoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);

            }
        });


        ResultSet resultSet = statement.executeQuery("SELECT wrk_AT FROM worker");

        while (resultSet.next()) {
            String WorkerAT = resultSet.getString("wrk_AT");
            BoxerDriverAT.addItem(WorkerAT);
        }


        BoxerDriverAT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }

        });

        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            try {

                String query = "INSERT INTO driver (drv_AT, drv_license , drv_route , drv_experience) " +
                        "VALUES(? , ? , ? , ?)";


                PreparedStatement insertStatementDriver = connection.prepareStatement(query);
                insertStatementDriver.setString(1, (String) BoxerDriverAT.getSelectedItem());
                insertStatementDriver.setString(2, (String) BoxDriverLicense.getSelectedItem());
                insertStatementDriver.setString(3, (String) BoxDriverRoute.getSelectedItem());
                insertStatementDriver.setString(4, textDriverExperience.getText());

                insertStatementDriver.executeUpdate();

            }catch(SQLException event){
                event.printStackTrace();
            }


                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);
    }

}
