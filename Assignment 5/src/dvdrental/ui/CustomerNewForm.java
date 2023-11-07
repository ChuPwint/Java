package dvdrental.ui;

import dvdrental.db.DbUtil;
import dvdrental.services.CustomerCrudService;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerNewForm extends JFrame {
    private static final int DEFAULT_WIDTH = 950;
    private static final int DEFAULT_HEIGHT = 600;
    private JComboBox<Country> jComboBox;
    private JComboBox<City> cityJComboBox;
    private int selected_ctry_id;
    private int selected_city_id;
    private boolean callComboActionEvent = false;
    private ArrayList<Country> countries = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();

    public CustomerNewForm() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        //add text-fields
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

        //add combo box
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

        //buttons
        JPanel buttonPanels = new JPanel();
        buttonPanels.setLayout(gbLayout);
        JButton createBtn = new JButton("Create");
        JButton clearBtn = new JButton("Clear");
        createBtn.setPreferredSize(new Dimension(100, 30));
        clearBtn.setPreferredSize(new Dimension(100, 30));
        buttonPanels.add(clearBtn, new GBC(0, 9).setIpad(100, 0)
                .setInsets(5, 10, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        buttonPanels.add(createBtn, new GBC(1, 9).setIpad(100, 0)
                .setInsets(5, 0, 20, 10)
                .setAnchor(GridBagConstraints.WEST));
        add(buttonPanels, BorderLayout.SOUTH);

        jComboBox.addActionListener(e -> {
            callComboActionEvent = true;
            Country country = (Country) jComboBox.getSelectedItem();
            if (country != null) {
                selected_ctry_id = country.getId();
                if(cityJComboBox.getItemCount() > 0){
                    cityJComboBox.removeAllItems();
                }
                try{
                    populateCityJComboBox();
                }catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to populate city combo box!");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Error in jComboActionListener:Country id null!");
            }
        });

        cityJComboBox.addActionListener(e -> {
            City city = (City) cityJComboBox.getSelectedItem();
            if (city != null) {
                selected_city_id = city.getId();
            }
        });

        Map<String, Object> addParams = new HashMap<>();
        createBtn.addActionListener(e -> {
            System.out.println("Has no digits: " + hasStringOnly("teosnfla"));
            System.out.println("Has digits: " + hasStringOnly("saogh23521"));
            boolean sameMail = false;
            try{
                sameMail = CustomerCrudService.getEmail(emailTxf.getText());
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Cannot process email results!");
            }

            if(fnameTxf.getText().isEmpty() || lnameTxf.getText().isEmpty() || emailTxf.getText().isEmpty() || addressTxf.getText().isEmpty()
                    || districtTxf.getText().isEmpty() || phoneTxf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Text-box is empty!");
            }else if(!(hasStringOnly(fnameTxf.getText())) || !(hasStringOnly(lnameTxf.getText()))){
                JOptionPane.showMessageDialog(null, "Please enter valid format for names!");
            }else if(!(checkEmailFormat(emailTxf.getText()))){
                JOptionPane.showMessageDialog(null, "Please enter a valid email.(e.g-ab12_ab.2343abc@gmail.com.myr)");
            }else if(sameMail){
                JOptionPane.showMessageDialog(null, "Already existing mail account!");
            }else if(addressTxf.getText().length() < 10){
                JOptionPane.showMessageDialog(null, "Please enter valid length for address(>=10)!");
            }else if(!(hasStringOnly(districtTxf.getText()))){
                JOptionPane.showMessageDialog(null, "Please enter (3-20) words{String only} for district!");
            }else if(!callComboActionEvent){
                JOptionPane.showMessageDialog(null, "Country must be selected!");
            }else if(!(isDigit(phoneTxf.getText()))){
                JOptionPane.showMessageDialog(null, "Please enter digits only!!");
            }else{
                try{
                    addParams.put("first_name", fnameTxf.getText());
                    addParams.put("last_name", lnameTxf.getText());
                    addParams.put("email", emailTxf.getText());

                    addParams.put("address", addressTxf.getText());
                    addParams.put("address_2", address2Txf.getText());
                    addParams.put("district", districtTxf.getText());
                    addParams.put("city_id", selected_city_id);
                    addParams.put("phone", phoneTxf.getText());
                    DbUtil.addNewCustomer(addParams);
                    int getUpdatedCount = DbUtil.updateRowCount();
                    if(getUpdatedCount == 2) {
                        fnameTxf.setText("");
                        lnameTxf.setText("");
                        emailTxf.setText("");
                        addressTxf.setText("");
                        address2Txf.setText("");
                        districtTxf.setText("");
                        phoneTxf.setText("");
                        cityJComboBox.removeAllItems();
                    }
                }catch (SQLException iE){
                    iE.printStackTrace();
                }
            }
        });

        clearBtn.addActionListener(e -> {
            fnameTxf.setText("");
            lnameTxf.setText("");
            emailTxf.setText("");
            addressTxf.setText("");
            address2Txf.setText("");
            districtTxf.setText("");
            phoneTxf.setText("");
            cityJComboBox.removeAllItems();
        });
    }

    public boolean checkEmailFormat(String a) {
        //available format
        //abc.123@Abc.com.sg
        boolean validFormat = false;
        String emailPattern = "\\w+\\.*\\w+@\\w+\\.\\w+\\.*\\w*";
        Pattern p = Pattern.compile(emailPattern);
        Matcher m = p.matcher(a);
        validFormat = m.matches();
        return validFormat;
    }

    public boolean isDigit(String a){
        boolean isDigit = false;
        String phonePattern = "\\d+";
        Pattern p = Pattern.compile(phonePattern);
        Matcher m = p.matcher(a);
        isDigit = m.matches();
        return isDigit;
    }

    public boolean hasStringOnly(String a){
        boolean hasNumbers = false;
        String pattern = "^\\d+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(a);
        hasNumbers = m.matches();
        return hasNumbers;
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