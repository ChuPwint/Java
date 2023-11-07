package dvdrental.ui;

import dvdrental.services.CustomerCrudService;

import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerSearchForm extends JFrame {

        private static final int DEFAULT_WIDTH = 950;
        private static final int DEFAULT_HEIGHT = 600;
        private int[] colWidth = {3, 20, 20, 200, 4};

        public CustomerSearchForm() {
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

                JPanel topPnl = new JPanel();
                GridBagLayout gbLayout = new GridBagLayout();
                topPnl.setLayout(gbLayout);

                JLabel fnameLbl = new JLabel("First Name");
                JLabel lnameLbl = new JLabel("Last Name");
                JLabel emailLbl = new JLabel("Email");
                JTextField fnameTxf = new JTextField();
                JTextField lnameTxf = new JTextField();
                JTextField emailTxf = new JTextField();

                topPnl.add(fnameLbl, new GBC(0, 0).setIpad(100, 0)
                                .setInsets(20, 0, 0, 30)
                                .setAnchor(GridBagConstraints.WEST));
                topPnl.add(fnameTxf, new GBC(0, 1).setIpad(100, 0)
                                .setInsets(5, 0, 0, 10)
                                .setAnchor(GridBagConstraints.WEST));

                topPnl.add(lnameLbl, new GBC(1, 0).setIpad(100, 0)
                                .setInsets(5, 0, 0, 10)
                                .setAnchor(GridBagConstraints.WEST));
                topPnl.add(lnameTxf, new GBC(1, 1).setIpad(100, 0)
                                .setInsets(5, 0, 0, 10)
                                .setAnchor(GridBagConstraints.WEST));

                topPnl.add(emailLbl, new GBC(2, 0).setIpad(100, 0)
                        .setInsets(5, 0, 0, 10)
                        .setWeight(100, 0)
                        .setAnchor(GridBagConstraints.WEST));
                topPnl.add(emailTxf, new GBC(2, 1).setIpad(100, 0)
                        .setInsets(5, 0, 0, 10)
                        .setWeight(100, 0)
                        .setAnchor(GridBagConstraints.WEST));

                JButton searchBtn = new JButton("Search");
                topPnl.add(searchBtn, new GBC(0, 2).setIpad(80, 10)
                                .setInsets(10, 0, 0, 10)
                                .setAnchor(GridBagConstraints.WEST));

                add(topPnl, BorderLayout.NORTH);

                JTable table = new JTable();
                table.setRowHeight(25);
                add(new JScrollPane(table), BorderLayout.CENTER);

                Map<String, String> searchParams = new HashMap<>();
                searchBtn.addActionListener(ev -> {
                        try {
                                // key will have to be used by service as well
                                // should create key enum for input fields
                                searchParams.put("fname", fnameTxf.getText());
                                searchParams.put("lname", lnameTxf.getText());
                                searchParams.put("email", emailTxf.getText());

                                // leave the task of constructing sql to CustomerCrudService
                                CachedRowSet rs = CustomerCrudService.searchCustomer(searchParams);

                                // use the row set as the data source for the table model
                                table.setModel(new CustomerTableModel(rs));
                                for(int i = 0; i < colWidth.length; i++) {
                                        table.getColumnModel().getColumn(i).setPreferredWidth(colWidth[i]);
                                }
                                // AbstractTableModel model = (AbstractTableModel) table.getModel();
                                // model.fireTableDataChanged();
                        }catch (SQLException e) {

                                // Exercise: handle the exception properly to display
                                // an appropriate error message
                                e.printStackTrace();
                        }
                });

                JPanel btmPnl = new JPanel();
                JButton nxtBtn = new JButton(">");
                nxtBtn.setPreferredSize(new Dimension(50, 30));
                JButton prevBtn = new JButton("<");
                prevBtn.setPreferredSize(new Dimension(50, 30));
                btmPnl.add(prevBtn);
                btmPnl.add(nxtBtn);
                add(btmPnl, BorderLayout.SOUTH);

                nxtBtn.addActionListener(ev -> {
                        try {
                                // casting shouldn't have any problem? why?
                                // why casting is required?
                                CustomerTableModel model = (CustomerTableModel) table.getModel();
                                model.nextPage();
                                // understand what this method is, and what is its purpose
                                //(any change between model and view)
                                model.fireTableDataChanged();
                        } catch (SQLException e) {

                                // add proper exception handling here
                                e.printStackTrace();
                        }
                });

                prevBtn.addActionListener(ev -> {
                        try {
                                CustomerTableModel model = (CustomerTableModel) table.getModel();
                                model.prevPage();
                                model.fireTableDataChanged();
                        } catch (SQLException e) {
                                // add proper exception handling
                                e.printStackTrace();
                        }
                });

        }
}

class CustomerTableModel extends AbstractTableModel {
        // crs is the only jdbc related object exposed to UI related
        private CachedRowSet crs = null;
        private String[] columnNames = { "Id", "First Name", "Last Name", "Email", "Active"};
        public CustomerTableModel(CachedRowSet rs) {
                this.crs = rs;
        }

        @Override
        public int getRowCount() {
                return crs.size();
        }

        @Override
        public int getColumnCount() {
                return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex){
                Object obj = null;
                try{
                        crs.beforeFirst();
                        crs.absolute(rowIndex + 1);
                        obj = crs.getObject(columnIndex + 1);
                } catch (SQLException e) {
                        // again do proper exception handling
                        System.out.println("Error in getValueAt(r, c): " + e.getMessage());
                }
                return  obj;
        }

        @Override
        public String getColumnName(int c) {
                return columnNames[c];
        }

        public void nextPage() throws SQLException{
                if(!crs.nextPage()) crs.previousPage();
        }

        public void prevPage() throws SQLException{
                if(!crs.previousPage()) crs.nextPage();
        }
}