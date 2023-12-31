import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This program demonstrates how to show a simple table
 *
 * @author Cay Horstmann
 * @version 1.11 2007-08-01
 */
public class Eg07aTableTest {
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                JFrame frame = new PlanetTableFrame();
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setVisible(true);
                        }
                });
        }
}

/**
 * This frame contains a table of planet data.
 */
class PlanetTableFrame extends JFrame {
        private Object[][] cells = {{"Mercury", 2440.0, 0, false, Color.YELLOW},
                {"Venus", 6052.0, 0, false, Color.YELLOW}, {"Earth", 6378.0, 1, false, Color.BLUE},
                {"Mars", 3397.0, 2, false, Color.RED}, {"Jupiter", 71492.0, 16, true, Color.ORANGE},
                {"Saturn", 60268.0, 18, true, Color.ORANGE},
                {"Uranus", 25559.0, 17, true, Color.BLUE}, {"Neptune", 24766.0, 8, true, Color.BLUE},
                {"Pluto", 1137.0, 1, false, Color.BLACK}};

        private String[] columnNames = {"Planet", "Radius", "No of Moons", "Gaseous", "Color"};

        private static final int DEFAULT_WIDTH = 400;
        private static final int DEFAULT_HEIGHT = 200;

        public PlanetTableFrame() {
                setTitle("TableTest");
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                final JTable table = new JTable(cells, columnNames);
                table.setAutoCreateRowSorter(true);
                JScrollPane sp = new JScrollPane(table);
                add(sp, BorderLayout.CENTER);
                JButton printButton = new JButton("Print");
                printButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                                try {
                                        table.print();
                                } catch (java.awt.print.PrinterException e) {
                                        e.printStackTrace();
                                }
                        }
                });
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(printButton);
                add(buttonPanel, BorderLayout.SOUTH);
        }


}
