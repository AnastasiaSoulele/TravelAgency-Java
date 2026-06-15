import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.System;

public class MenuTravelAgency extends JDialog {
    private JPanel menu;
    private JTextField chooseField;
    private JComboBox InsertBox;
    private JLabel LabelInsert;
    private JButton InsertButton;
    private JButton ModifyButton;
    private JButton DeleteButton;
    private JLabel TripInfoLabel;
    private JButton TripButton;
    private JLabel DepartureDate;
    private JTextField textDeparture;
    private JLabel ReturnDate;
    private JTextField textReturn;
    private JLabel ClientLabel;
    private JTextField textClient;
    private JButton findButton;
    private JLabel BranchManagerLabel;
    private JButton ManagerButton;
    private JLabel BranchEmployeeLabel;
    private JButton EmployeeButton;
    private JLabel logInfoLabel;
    private JButton LogButton;
    private JTable InfoTable;
    private JTabbedPane Information;
    private JComboBox BoxManagerBranch;
    public String TableSelected;


    public MenuTravelAgency(JFrame parent) {
        super(parent);

        setTitle("Menu of Actions");
        setContentPane(menu);
        setSize(800, 800);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Insertion Activate Box Button
        InsertBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableSelected = (String) InsertBox.getSelectedItem();

            }
        });

        InsertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertData();

            }
        });

        //Edw teleiwnei to MenuTravelAgency
        TripButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowInfoAboutTrip();
            }
        });


        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindTheClient();
            }
        });


        ManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BranchManagerInfo();
            }
        });



        EmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BranchEmployeeInfo();

            }
        });




        LogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInfo();

            }
        });


        setVisible(true);

    }

    public void InsertData() {
        TableSelected = (String) InsertBox.getSelectedItem();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "@");

            System.out.println("The connection to Travel Agency: Travel Booth achivied: " + connection);
            System.out.println("---------------------------------------------------------------------");


//----------------------------------------------------------------------------------------------------------------------
            if (TableSelected.equals("branch")) {

                branch branch = new branch(null);
            }


//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("worker")) {

                worker worker = new worker(null);

            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("guide")) {

                worker worker = new worker(null);
                guide guide = new guide(null);

            }
//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("admin")) {


                worker worker = new worker(null);
                admin admin = new admin(null);

            }
//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("driver")) {

                worker worker = new worker(null);
                driver driver = new driver(null);
            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("trip")) {

                trip trip = new trip(null);

            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("phones")) {

                System.out.println("----------------------------------------------------------");
                System.out.println("You have to insert a new branch first : ");

                branch branch = new branch(null);

                phones phones = new phones(null);

            }
//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("event")) {

                System.out.println("You have to insert a new trip first");

                trip trip = new trip(null);
                event event = new event(null);

            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("destination")) {

                destination destination = new destination(null);}


//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("travel_to")) {

                System.out.println("----------------------------------------------------------");
                System.out.println("Firstly you have to insert a new trip: ");

                trip trip = new trip(null);


                System.out.println("--------------------------------------------------------------------");
                System.out.println("Secondly,you have to insert a destination:");

                destination destination = new destination(null);

                travel_to travelTo = new travel_to(null);

            }

//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("manages")) {

                System.out.println("Firstly,you have to insert a new worker to add a new admin as a manager: ");
                worker worker = new worker(null);
                admin admin = new admin(null);

                System.out.println("Secondly, you have to insert a new worker to add a new branch code as a manager branch code: ");
                branch branch = new branch(null);


                manages manages = new manages(null);
            }


//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("reservation")) {

                System.out.println("Firstly, you have to insert a new trip to add a new reservation: ");
                trip trip = new trip(null);


                reservation reservation = new reservation(null);

            }

//----------------------------------------------------------------------------------------------------------------------
            if (TableSelected.equals("languages")) {

                System.out.println("Firstly, you have to insert a new guide to add a new language to the guide: ");
                worker worker = new worker(null);
                guide guide = new guide(null);

                languages languages = new languages(null);

            }

//----------------------------------------------------------------------------------------------------------------------
            if (TableSelected.equals("offers")) {

                offers offers = new offers(null);

            }


//----------------------------------------------------------------------------------------------------------------------

            if (TableSelected.equals("reservation_offers")) {

                reservation_offers reservation_offers = new reservation_offers(null);
            }


//----------------------------------------------------------------------------------------------------------------------
          if (TableSelected.equals("it")){


              worker worker5=new worker(null);
              it it= new it(null);

          }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//----------------------------------------------------------------------------------------------------------------------
    public void ShowInfoAboutTrip() {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "@");

            System.out.println("The connection to Travel Agency: Travel Booth achivied: " + connection);
            System.out.println("---------------------------------------------------------------------");


            String query = "SELECT tr_departure, tr_return , tr_maxseats , tr_cost , tr_br_code,res_tr_id,tr_maxseats-res_tr_id,worker.wrk_name, worker.wrk_lname ,wd.wrk_name, wd.wrk_lname FROM trip INNER JOIN reservation ON tr_id=res_tr_id INNER JOIN guide ON trip.tr_gui_AT=guide.gui_AT INNER JOIN worker ON guide.gui_AT=worker.wrk_AT INNER JOIN driver ON trip.tr_drv_AT=driver.drv_AT INNER JOIN worker wd ON driver.drv_AT=wd.wrk_AT WHERE tr_departure >= ? AND tr_return <= ?";
            PreparedStatement statement= connection.prepareStatement(query);

            statement.setString(1,textDeparture.getText());
            statement.setString(2,textReturn.getText());

            ResultSet resultSet=statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            DefaultTableModel model = (DefaultTableModel) InfoTable.getModel();

            int columns = resultSetMetaData.getColumnCount();
            String[] columnName= new String[columns];
            for(int i=0; i<columns; i++)
                columnName[i]=resultSetMetaData.getColumnName(i+1);

            model.setColumnIdentifiers(columnName);
            String trdeparture, trreturn, maxseats,cost ,brcode ,countRes,emptySeats,guideName,guideLname,driverName,driverLname ;
            while (resultSet.next()) {
                trdeparture=resultSet.getString(1);
                trreturn=resultSet.getString(2);
                maxseats=resultSet.getString(3);
                cost=resultSet.getString(4);
                brcode=resultSet.getString(5);
                countRes=resultSet.getString(6);
                emptySeats=resultSet.getString(7);
                guideName=resultSet.getString(8);
                guideLname=resultSet.getString(9);
                driverName=resultSet.getString(10);
                driverLname=resultSet.getString(11);
                String[] row = {trdeparture,trreturn,maxseats,cost,brcode,countRes,emptySeats,guideName,guideLname,driverName,driverLname};
                model.addRow(row);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


 //---------------------------------------------------------------------------------------------------------------------
    public void FindTheClient(){

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "@");

            CallableStatement statement = connection.prepareCall("CALL FindTheClient(?)");
             statement.setString(1,textClient.getText());

             ResultSet result = statement.executeQuery();
             ResultSetMetaData resultSetMetaData = result.getMetaData();
             DefaultTableModel model = (DefaultTableModel) InfoTable.getModel();

            int columns = resultSetMetaData.getColumnCount();
            String[] columnName= new String[columns];
            for(int i=0; i<columns; i++)
                columnName[i]=resultSetMetaData.getColumnName(i+1);

            model.setColumnIdentifiers(columnName);
            String reservation_name,reservation_lname,advance ;
            while (result.next()) {
                reservation_name=result.getString(1);
                reservation_lname=result.getString(2);
                advance=result.getString(3);
                String[] row = {reservation_name,reservation_lname,advance};
                model.addRow(row);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
    public void BranchManagerInfo(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "@");


            String query = "SELECT br_code,br_street,br_num,br_city, wrk_name, wrk_lname,tr_cost,res_tr_id,res_tr_id*tr_cost FROM branch INNER JOIN manages ON br_code=mng_br_code INNER JOIN admin ON mng_adm_AT=adm_AT INNER JOIN worker ON wrk_AT=adm_AT INNER JOIN trip ON tr_br_code=br_code INNER JOIN reservation ON res_tr_id=tr_id ";
            PreparedStatement statement= connection.prepareStatement(query);

            ResultSet resultSet=statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            DefaultTableModel model = (DefaultTableModel) InfoTable.getModel();

            int columns = resultSetMetaData.getColumnCount();
            String[] columnName= new String[columns];
            for(int i=0; i<columns; i++)
                columnName[i]=resultSetMetaData.getColumnName(i+1);

            model.setColumnIdentifiers(columnName);
            String brcode,brstreet,brnum,brcity,wrkName,wrkLname,countResID,cost,incount;

            while (resultSet.next()) {

                brcode=resultSet.getString(1);
                brstreet=resultSet.getString(2);
                brnum=resultSet.getString(3);
                brcity=resultSet.getString(4);
                wrkName=resultSet.getString(5);
                wrkLname=resultSet.getString(6);
                countResID=resultSet.getString(7);
                cost=resultSet.getString(8);
                incount=resultSet.getString(9);

                String[] row = {brcode,brstreet,brnum,brcity,wrkName,wrkLname,countResID,cost,incount};
                model.addRow(row);
            }

        } catch (SQLException e) {
         e.printStackTrace();
      }

   }

//----------------------------------------------------------------------------------------------------------------------

    public void BranchEmployeeInfo() {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "@");


            String query = "SELECT br_code, br_num, br_street,br_city, wrk_name, wrk_lname,wrk_salary,wrk_br_code*wrk_salary FROM branch INNER JOIN worker ON br_code=wrk_br_code ";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            DefaultTableModel model = (DefaultTableModel) InfoTable.getModel();

            int columns = resultSetMetaData.getColumnCount();
            String[] columnName = new String[columns];
            for (int i = 0; i < columns; i++)
                columnName[i] = resultSetMetaData.getColumnName(i + 1);

            model.setColumnIdentifiers(columnName);
            String br_code, br_num, br_street, br_city, wrk_name, wrk_lname, wrk_salary, countMoney;

            while (resultSet.next()) {

                br_code = resultSet.getString(1);
                br_street = resultSet.getString(2);
                br_num = resultSet.getString(3);
                br_city = resultSet.getString(4);
                wrk_name = resultSet.getString(5);
                wrk_lname = resultSet.getString(6);
                wrk_salary = resultSet.getString(7);
                countMoney = resultSet.getString(8);

                String[] row = {br_code, br_num, br_street, br_city, wrk_name, wrk_lname, wrk_salary, countMoney};
                model.addRow(row);
            }


            } catch (SQLException e) {
               e.printStackTrace();
        }

    }

//----------------------------------------------------------------------------------------------------------------------


    public void LogInfo(){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/travel_agency",
                    "root",
                    "@");

            String query = "SELECT line,user,action,it_AT,start_date,end_date FROM Audit_log INNER JOIN it INNER JOIN worker ON it_AT=wrk_AT";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            DefaultTableModel model = (DefaultTableModel) InfoTable.getModel();

            int columns = resultSetMetaData.getColumnCount();
            String[] columnName = new String[columns];
            for (int i = 0; i < columns; i++)
                columnName[i] = resultSetMetaData.getColumnName(i + 1);

            model.setColumnIdentifiers(columnName);
            String line,user,action,it_AT,start_date,end_date;

            while (resultSet.next()) {

                line = resultSet.getString(1);
                user = resultSet.getString(2);
                action = resultSet.getString(3);
                it_AT = resultSet.getString(4);
                start_date = resultSet.getString(5);
                end_date = resultSet.getString(6);

                String[] row = {line,user,action,it_AT,start_date,end_date};
                model.addRow(row);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
