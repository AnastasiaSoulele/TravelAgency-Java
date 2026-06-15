import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class admin extends JDialog{
    private JPanel admin;
    private JComboBox AdmTypeBox;
    private JTextField textAdminDiploma;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox BoxerAdminAT;


    public admin(JFrame parent) throws SQLException {
        super(parent);

        setTitle("admin");
        setContentPane(admin);
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
            BoxerAdminAT.addItem(WorkerAT);
        }

        BoxerAdminAT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }

        });


        AdmTypeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);

            }
        });



        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String query = "INSERT INTO admin (adm_AT,adm_type,adm_diploma) " +
                            "VALUES(? , ? , ?)";


                    PreparedStatement insertStatementAdmin = connection.prepareStatement(query);
                    insertStatementAdmin.setString(1, (String) BoxerAdminAT.getSelectedItem());
                    insertStatementAdmin.setString(2, (String) AdmTypeBox.getSelectedItem());
                    insertStatementAdmin.setString(3, textAdminDiploma.getText());

                    insertStatementAdmin.executeUpdate();


                } catch(SQLException event){
                    event.printStackTrace();
                }

                Result.setText("The insertion achieved");

            }

        });

        setVisible(true);

    }
}








