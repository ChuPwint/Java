
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

class CustomerAdding extends JFrame {

    private static final int DEFAULT_WIDTH = 480;
    private static final int DEFAULT_HEIGHT = 500;
    JLabel l1 = new JLabel("first name:", SwingConstants.RIGHT);
    JLabel l2 = new JLabel("last name:", SwingConstants.RIGHT);
    JLabel l3 = new JLabel("email:", SwingConstants.RIGHT);
    JLabel activeLbl = new JLabel("active:", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("address:", SwingConstants.RIGHT);
    JLabel l5 = new JLabel("address2:", SwingConstants.RIGHT);
    JLabel ph = new JLabel("phone:", SwingConstants.RIGHT);
    JLabel l6 = new JLabel("district:", SwingConstants.RIGHT);
    JLabel storeId = new JLabel("store id:", SwingConstants.RIGHT);

    JLabel l7 = new JLabel("country:", SwingConstants.RIGHT);
    JLabel l8 = new JLabel("city:", SwingConstants.RIGHT);
    JTextField t1 = new JTextField();
    int selectedCountryId;
    int selectedCityId;
    int selectedStoreId;
    String selectedCity;



    JTextField t2 = new JTextField();

    JTextField t3 = new JTextField();

    JCheckBox c = new JCheckBox();

    JTextField t4 = new JTextField();

    JTextField t5 = new JTextField();
    JTextField phno = new JTextField();

    JTextField t6 = new JTextField();
    JCheckBox storeId1 = new JCheckBox("1");
    JCheckBox storeId2 = new JCheckBox("2");

    JComboBox<String> countries = new JComboBox<String>();

    JComboBox<String> cities = new JComboBox<>();

    String url = "jdbc:mysql://localhost:3306/sakila";
    String user = "root";
    String pwd = "asdfgh";
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;


    public CustomerAdding() {

        setTitle(" New Customer Registration Form ");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        JPanel topPnl = new JPanel();
        GridLayout gridLayout = new GridLayout();

        gridLayout.setRows(12);
        gridLayout.setColumns(3);
        gridLayout.setHgap(8);
        gridLayout.setVgap(8);
        topPnl.setLayout(gridLayout);


        topPnl.add(l1);
        topPnl.add(t1);
        topPnl.add(new JPanel());

        topPnl.add(l2);
        topPnl.add(t2);
        topPnl.add(new JPanel());

        topPnl.add(l3);
        topPnl.add(t3);
        topPnl.add(new JPanel());

        topPnl.add(activeLbl);
        topPnl.add(c);
        c.setSelected(true);

        c.isSelected();
        topPnl.add(new JPanel());

        topPnl.add(l4);
        topPnl.add(t4);
        topPnl.add(new JPanel());

        topPnl.add(l5);
        topPnl.add(t5);
        topPnl.add(new JPanel());

        topPnl.add(ph);
        topPnl.add(phno);
        topPnl.add(new JPanel());

        topPnl.add(l6);
        topPnl.add(t6);
        topPnl.add(new JPanel());

        topPnl.add(storeId);
        topPnl.add(storeId1);
        storeId1.setSelected(true);
        storeId1.addActionListener(e -> {
            if (storeId1.isSelected()) {
                storeId2.setSelected(false);
            } else {
                storeId2.setSelected(true);
            }
        });
        topPnl.add(storeId2);
        storeId2.addActionListener(e -> {
            if (storeId2.isSelected()) {
                storeId1.setSelected(false);
            } else {
                storeId1.setSelected(true);
            }
        });


        topPnl.add(l7);
        setCountries();
        topPnl.add(countries);
        countries.addActionListener(e -> {
            selectedCountryId = countries.getSelectedIndex() + 1;
            setCities();
            System.out.println(" country name: " + countries.getSelectedItem().toString());
            System.out.println(" country Id: " + selectedCountryId);

        });
        topPnl.add(new JPanel());

        topPnl.add(l8);
        cities.addActionListener(e -> {
            if (cities.getSelectedItem() != null) {
                selectedCity = cities.getSelectedItem().toString();

            } else {
                selectedCity = null;
            }
            System.out.println(" city name: " + selectedCity);
            //System.out.println(" city id: " + selectedCityId);
        });



        topPnl.add(cities);
        topPnl.add(new JPanel());

        JPanel lowPnl = new JPanel();
        JButton clear = new JButton("Clear");
        clear.addActionListener((ActionEvent e) -> {
            butClearPerform();
        });
        JButton save = new JButton("Save");
        save.addActionListener((ActionEvent e) -> {
            butSavePerform();
        });
        lowPnl.add(clear);
        lowPnl.add(save);

        this.add(topPnl, BorderLayout.CENTER);
        this.add(lowPnl, BorderLayout.SOUTH);
    }

    //get countries data from database to combo box
    private void setCountries() {
        try {
            con = DriverManager.getConnection(url, user, pwd);
            st = con.createStatement();
            String s = "select country from country";
            rs = st.executeQuery(s);
            while (rs.next()) {
                countries.addItem(rs.getString(1));
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "set countries: ERROR");
        } finally {
            try {
                con.close();
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "set countries: ERROR CLOSE");
            }
        }
    }

    //get cities data from database to combo box
    private void setCities() {
        try {
            if (cities.getItemCount() > 0) {
                cities.removeAllItems();
            }
            con = DriverManager.getConnection(url, user, pwd);
            st = con.createStatement();
            String s = "SELECT * FROM city ";
            rs = st.executeQuery(s);
            while (rs.next()) {
                if (selectedCountryId == rs.getInt("country_id")) {
                        cities.addItem(rs.getString("city"));
                }

            }
        }catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "set cities: ERROR");
        }finally {
            try {
                con.close();
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "set cities: ERROR CLOSE");
            }
        }
    }
    private void butClearPerform() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        phno.setText("");
        t6.setText("");
        countries.setSelectedIndex(0);
        cities.setSelectedIndex(0);
        storeId1.setSelected(true);
        storeId2.setSelected(false);
        c.setSelected(true);
    }

    public Blob getBlob_From() throws SQLException {
        con = DriverManager.getConnection(url, user, pwd);
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet bRs = st.executeQuery("SELECT location FROM address");
        bRs.last();
        Blob geo = bRs.getBlob(1);
        return geo;
    }

    public int getCityId() throws SQLException{
        con = DriverManager.getConnection(url, user, pwd);
        st = con.createStatement();
        String s = "SELECT * FROM city ";
        rs = st.executeQuery(s);
        int cityId = 0;
        while(rs.next()){
            if(rs.getString("city").equals(selectedCity)){
                cityId = rs.getInt("city_id");
            }
        }
        return cityId;
    }



    public int getLastAddressId() throws SQLException {
        con = DriverManager.getConnection(url, user, pwd);
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = st.executeQuery("SELECT * FROM address");
        rs.last();
        int id = rs.getInt(1);
        return id;
    }

    private void butSavePerform() {
        if (t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") ||
                t4.getText().equals("") || t5.getText().equals("") || phno.getText().equals("") ||
                t6.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter All Data !");

        } else {
            try {
                con = DriverManager.getConnection(url, user, pwd);
                String query1 = "INSERT INTO address ( address, address2 , district , " +
                        "city_id , phone, location )" +
                        "VALUES(?,?,?,?,?,?)";

                PreparedStatement prepStmt1 = con.prepareStatement(query1);
                Blob geo = getBlob_From();
                selectedCityId = getCityId();

                prepStmt1.setString(1, t4.getText());
                prepStmt1.setString(2, t5.getText());
                prepStmt1.setString(3, t6.getText());
                prepStmt1.setInt(4, selectedCityId);
                prepStmt1.setString(5, phno.getText());
                prepStmt1.setBlob(6, geo);
                prepStmt1.executeUpdate();


                int addressId = getLastAddressId();
                String query2 = "insert into customer (store_id ,first_name, last_name, email ," +
                        " address_id ,active )values(?, ?, ?, ?, ?,? )";
                PreparedStatement prepStmt2 = con.prepareStatement(query2);
                if (storeId1.isSelected()) {
                    selectedStoreId = 1;
                }
                if (storeId2.isSelected()) {
                    selectedStoreId = 2;
                }
                prepStmt2.setInt(1, selectedStoreId);
                prepStmt2.setString(2, t1.getText());
                prepStmt2.setString(3, t2.getText());
                prepStmt2.setString(4, t3.getText());
                prepStmt2.setInt(5, addressId);
                if (c.isSelected()) {
                    prepStmt2.setInt(6, 1);
                } else {
                    prepStmt2.setInt(6, 0);
                }
                prepStmt2.executeUpdate();

                JOptionPane.showMessageDialog(this, "Add Data Successfully !");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "ERROR");
            } finally {
                try {
                    con.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ERROR CLOSE");
                }
            }
        }
    }
}


