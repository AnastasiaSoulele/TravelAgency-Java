import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class languages extends JDialog {
    private JPanel languages;
    private JTextField textLngLanguage;
    private JButton confirmedButton;
    private JLabel Result;
    private JComboBox lngBoxer;

    public languages(JFrame parent) throws SQLException {
        super(parent);

        setTitle("languages");
        setContentPane(languages);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_agency",
                "root",
                "@");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT gui_AT FROM guide");

        while (resultSet.next()) {
            String GuideAT = resultSet.getString("gui_AT");
            lngBoxer.addItem(GuideAT);
        }


        lngBoxer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(true);

            }
        });



        confirmedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            try {


                String query = "INSERT INTO languages ( lng_gui_AT ,lng_language) " +
                        "VALUES( ? , ? )";

                PreparedStatement insertStatementLanguage = connection.prepareStatement(query);

                insertStatementLanguage.setString(1, (String) lngBoxer.getSelectedItem());
                insertStatementLanguage.setString(2, textLngLanguage.getText());

                insertStatementLanguage.executeUpdate();



            }catch(SQLException event){
                event.printStackTrace();
            }


        Result.setText("The insertion achieved");

       }

      });

        setVisible(true);


    }
}
