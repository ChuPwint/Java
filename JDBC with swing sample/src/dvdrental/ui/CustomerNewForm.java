package dvdrental.ui;

import dvdrental.db.DbUtil;
import dvdrental.services.CustomerCrudService;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class CustomerNewForm extends JFrame {
    private static final int DEFAULT_WIDTH = 950;
    private static final int DEFAULT_HEIGHT = 600;
    private JComboBox<Country> jComboBox;
    private JComboBox<City> cityJComboBox;
    private int selected_ctry_id;
    private int selected_city_id;
    private ArrayList<Country> countries = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();

    public CustomerNewForm() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JPanel newCustomerPnl = new JPanel();
        GridBagLayout gbLayout = new GridBagLayout();
        newCustomerPnl.setLayout(gbLayout);

        JLabel fnameLbl = new JLabel("First Name:");
        JLabel lnameLbl = new JLabel("Last Name:");
        JLabel emailLbl = new JLabel("Email:");
        JLabel addressLbl = new JLabel("Address:");
        JLabel address2Lbl = new JLabel("Address 2:");
        JLabel districtLbl = new JLabel("District");
        JLabel phoneLbl = new JLabel("Phone");

        JTextField fnameTxf = new JTextField();
        JTextField lnameTxf = new JTextField();
        JTextField emailTxf = new JTextField();
        JTextField addressTxf = new JTextField();
        JTextField address2Txf = new JTextField();
        JTextField districtTxf = new JTextField();
        JTextField phoneTxf = new JTextField();

        newCustomerPnl.add(fnameLbl, new GBC(0, 0).setIpad(50, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        newCustomerPnl.add(fnameTxf, new GBC(1, 0).setIpad(150, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));

        newCustomerPnl.add(lnameLbl, new GBC(0, 1).setIpad(50, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        newCustomerPnl.add(lnameTxf, new GBC(1, 1).setIpad(150, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));

        newCustomerPnl.add(emailLbl, new GBC(0, 2).setIpad(50, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        newCustomerPnl.add(emailTxf, new GBC(1, 2).setIpad(150, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));

        newCustomerPnl.add(addressLbl, new GBC(0, 3).setIpad(50, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        newCustomerPnl.add(addressTxf, new GBC(1, 3).setIpad(200, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));

        newCustomerPnl.add(address2Lbl, new GBC(0, 4).setIpad(50, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        newCustomerPnl.add(address2Txf, new GBC(1, 4).setIpad(200, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));

        newCustomerPnl.add(districtLbl, new GBC(0, 5).setIpad(50, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        newCustomerPnl.add(districtTxf, new GBC(1, 5).setIpad(100, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));

        newCustomerPnl.add(phoneLbl, new GBC(0, 6).setIpad(50, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        newCustomerPnl.add(phoneTxf, new GBC(1, 6).setIpad(100, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));

        try {
            jComboBox = new JComboBox<>();
            cityJComboBox = new JComboBox<>();
            populateJComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel countryLbl = new JLabel("Country");
        newCustomerPnl.add(countryLbl, new GBC(0, 7).setIpad(50, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        newCustomerPnl.add(jComboBox, new GBC(1, 7).setIpad(35, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        JLabel cityLbl = new JLabel("City");
        newCustomerPnl.add(cityLbl, new GBC(0, 8).setIpad(50, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        newCustomerPnl.add(cityJComboBox, new GBC(1, 8).setIpad(100, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        add(newCustomerPnl, BorderLayout.CENTER);

        jComboBox.addActionListener(e -> {
            Country country = (Country) jComboBox.getSelectedItem();
            try{
                selected_ctry_id = country.getId();
                if(cityJComboBox.getItemCount() > 0){
                    cityJComboBox.removeAllItems();
                }
                populateCityJComboBox();
            }catch(NullPointerException n){
                JOptionPane.showMessageDialog(null, "Select a country!");
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        cityJComboBox.addActionListener(e -> {
            City city = (City)cityJComboBox.getSelectedItem();
            try{
                selected_city_id = city.getId();
            }catch (NullPointerException n){
                JOptionPane.showMessageDialog(null, "Select a city!");
            }
            System.out.println("City id(:" + selected_city_id);
        });

        JPanel btmPnl = new JPanel();
        JButton createBtn = new JButton("Create");
        createBtn.setPreferredSize(new Dimension(100, 30));
        btmPnl.add(createBtn);
        add(btmPnl, BorderLayout.PAGE_END);

        Map<String, Object> addParams = new HashMap<>();
        createBtn.addActionListener(e -> {
            addParams.put("first_name", fnameTxf.getText());
            addParams.put("last_name", lnameTxf.getText());
            addParams.put("email", emailTxf.getText());

            addParams.put("address", addressTxf.getText());
            addParams.put("address_2", address2Txf.getText());
            addParams.put("district", districtTxf.getText());
            addParams.put("city_id", selected_city_id);
            addParams.put("phone", phoneTxf.getText());
            try {
                DbUtil.addNewCustomer(addParams);
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Add customer error!");
            }
        });
    }

    public void populateJComboBox() throws SQLException {
        CachedRowSet rs = CustomerCrudService.getCountries();
        while (rs.next()) {
            Country country = new Country(rs.getInt(1), rs.getString(2));
            countries.add(country);
            jComboBox.addItem(country);
        }
    }

    public void populateCityJComboBox() throws SQLException {
        CachedRowSet cityRs = CustomerCrudService.getCities();
        while (cityRs.next()) {
            if(cityRs.getInt(3) == selected_ctry_id) {
                City city = new City(cityRs.getInt(1), cityRs.getString(2));
                cities.add(city);
                cityJComboBox.addItem(city);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            CustomerNewForm frame = new CustomerNewForm();
            frame.setTitle("DVD Rental Store (Create New Customer)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}

class Country {
    private int id;
    private String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class City {
    private int id;
    private String name;
    private int countryId;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public City(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountryId() { return countryId; }

    @Override
    public String toString() {
        return this.name;
    }
}
