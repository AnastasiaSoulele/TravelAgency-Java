import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class worker extends JDialog {
    private JTextField textWrkAT;
    private JTextField textWrkName;
    private JTextField textWrkLname;
    private JTextField textWrkSalary;
    private JTextField textWrkBrCode;
    private JPanel worker;
    private JButton confirmedButton;
    private JComboBox BoxWorkerBrCode;
    private JLabel Result;


    public worker(JFrame parent) throws SQLException {
        super(parent);

        setTitle("Worker");
        setContentPane(worker);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT br_code FROM branch");
        while (resultSet.next()) {

            int WorkerBrCode = resultSet.getInt("br_code");
            BoxWorkerBrCode.addItem(WorkerBrCode);
        }


        BoxWorkerBrCode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                }

            });



        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {

                        String query = "INSERT INTO worker (wrk_AT , wrk_name , wrk_lname , wrk_salary, wrk_br_code) " +
                                "VALUES( ? , ? , ? , ? , ? )";

                        PreparedStatement insertStatement = connection.prepareStatement(query);

                        insertStatement.setString(1, textWrkAT.getText());
                        insertStatement.setString(2, textWrkName.getText());
                        insertStatement.setString(3, textWrkLname.getText());
                        insertStatement.setFloat(4, Float.parseFloat(textWrkSalary.getText()));
                        insertStatement.setInt(5, (Integer) BoxWorkerBrCode.getSelectedItem());

                        insertStatement.executeUpdate();

                    } catch (SQLException event) {
                        event.printStackTrace();

                    }
                    Result.setText("The insertion achieved");
                }

        });
          setVisible(true);
    }

}