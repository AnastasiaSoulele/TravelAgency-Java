import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class guide extends JDialog {
    private JLabel gui_cv;
    private JTextField textGuiCV;
    private JPanel guide;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxerGuideAT;


    public guide(JFrame parent) throws SQLException {
        super(parent);

        setTitle("guide");
        setContentPane(guide);
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
            BoxerGuideAT.addItem(WorkerAT);
        }


        BoxerGuideAT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }

        });

        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                        String query = "INSERT INTO guide (gui_AT , gui_cv) " +
                                "VALUES(? , ? )";

                        PreparedStatement insertStatementGuide = connection.prepareStatement(query);
                        insertStatementGuide.setString(1, (String) BoxerGuideAT.getSelectedItem());
                        insertStatementGuide.setString(2, textGuiCV.getText());

                        insertStatementGuide.executeUpdate();


                } catch(SQLException event){
                    event.printStackTrace();
                }


                Result.setText("The insertion achieved");
            }
        });

        setVisible(true);
    }

}
