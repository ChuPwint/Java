import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.SQLException;

public class Eg07cEg07Table {
        static {
                FlatIntelliJLaf.setup();
                /***
                UIManager.put( "Component.arc", 0 );
                UIManager.put("TableHeader.background", Color.DARK_GRAY);
                UIManager.put("TableHeader.foreground", Color.WHITE);
                UIManager.put("Table.foreground", Color.WHITE);
                 ***/
        }
        public static void main(String[] args) {
                EventQueue.invokeLater(() -> {
                        Eg07CustomerTblFrame frame = new Eg07CustomerTblFrame();
                        frame.setTitle("DVD Rental Store");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}

class Eg07CustomerTblFrame extends JFrame {
        private static final int DEFAULT_WIDTH = 950;
        private static final int DEFAULT_HEIGHT = 600;
        private int[] colWidth = {3, 20, 20, 200, 4};
        public Eg07CustomerTblFrame() {
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

                JPanel topPnl = new JPanel();
                // တကယ်တမ်းက GridBagLayout သုံးသင့်တယ်
                GridLayout gridLayout = new GridLayout();
                gridLayout.setRows(4);
                gridLayout.setColumns(5);
                gridLayout.setHgap(8);
                gridLayout.setVgap(8);
                topPnl.setLayout(gridLayout);
                JLabel fnameLbl = new JLabel("First Name");
                JLabel lnameLbl = new JLabel("Last Name");
                JTextField fnameTxf = new JTextField();
                JTextField lnameTxf = new JTextField();

                JComponent[][] cells = new JComponent[4][5];
                for(int i = 0; i < cells.length; i++) {
                        for (int j = 0; j < 5; j++) {
                                cells[i][j] = new JPanel();
                        }
                }

                cells[0][0] = fnameLbl;
                cells[0][1] = lnameLbl;
                cells[1][0] = fnameTxf;
                cells[1][1] = lnameTxf;
                JButton searchBtn = new JButton("Search");
                searchBtn.setPreferredSize(new Dimension(15,40));
                cells[2][0] = searchBtn;

                for(int i = 0; i < cells.length; i++) {
                        for (int j = 0; j < 5; j++) {
                                topPnl.add(cells[i][j]);
                        }
                }

                add(topPnl, BorderLayout.NORTH);

                // table မှာ ပြပေးဖို့ data ထုတ်ပေးမဲ့ model
                Eg07CustTblModel model = new Eg07CustTblModel();
                JTable table = new JTable(model);
                table.setRowHeight(25);
                for(int i = 0; i < colWidth.length; i++) {
                        table.getColumnModel().getColumn(i).setPreferredWidth(colWidth[i]);
                }
                // table ကို scroll လဲလုပ်လို့ရအောင်
                add(new JScrollPane(table));

                // Table row ပေါ်မှာ right click နှိပ်ပြီး pop-up menu ပေါ်လာအောင်
                JPopupMenu popup = new JPopupMenu();
                JMenuItem viewMnuItm = new JMenuItem("View Details");
                JMenuItem editMnuItm = new JMenuItem("Edit");
                JMenuItem deleteMnuItm = new JMenuItem("Delete");
                JMenuItem addMnuItm = new JMenuItem("Add");
                deleteMnuItm.addActionListener(ev -> System.out.println("Are you sure?"));
                popup.add(viewMnuItm);
                popup.add(editMnuItm);
                popup.add(deleteMnuItm);
                popup.add(addMnuItm);

                table.setComponentPopupMenu(popup);
        }
}

class Eg07CustTblModel extends AbstractTableModel {

        CachedRowSet crs = null;
        private String[] columnNames = { "Id", "First Name", "Last Name", "Email", "Active"};
        public Eg07CustTblModel() {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "12345";

                try {
                        crs = RowSetProvider.newFactory().createCachedRowSet();
                        String query = "SELECT customer_id, first_name, last_name, email, active FROM customer ORDER BY customer_id";
                        crs.setCommand(query);
                        crs.setUrl(url);
                        crs.setUsername(user);
                        crs.setPassword(pwd);
                        //
                        crs.setPageSize(40);
                        crs.execute();
                        //crs.nextPage();
                        //crs.nextPage();
                } catch (SQLException e) {
                        System.out.println(e.getErrorCode());
                        e.printStackTrace();
                }
        }

        @Override
        public int getRowCount() {
                return 40;
        }

        @Override
        public int getColumnCount() {
                return 5;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex){
                Object obj = null;
                try{
                        // rowIndex, columnIndex က array လိုပဲ သုညကစတယ်
                        // RowSet ကတော့ row နဲ့ col နံပါတ်တွေက ၁ ကစတဲ့အတွက် ၁ ပေါင်းရရတာ
                        crs.absolute(rowIndex + 1);
                        obj = crs.getObject(columnIndex + 1);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return  obj;
        }

        public String getColumnName(int c) {
                return columnNames[c];
        }
}