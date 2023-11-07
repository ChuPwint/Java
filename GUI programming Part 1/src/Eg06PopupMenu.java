import javax.swing.*;
import java.awt.*;

public class Eg06PopupMenu {
        public static void main(String[] args) {
                EventQueue.invokeLater(() ->
                {
                        JFrame frame = new Eg06Frame();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}

class Eg06Frame extends JFrame {
        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 200;

        public Eg06Frame() {
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
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

                JPanel panel = new JPanel();
                panel.setComponentPopupMenu(popup);
                add(panel);

        }
}
