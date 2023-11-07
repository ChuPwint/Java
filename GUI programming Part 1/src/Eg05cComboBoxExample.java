import java.awt.*;
import javax.swing.*;

/**
 * @author Cay Horstmann
 * @version 1.36 2018-04-10
 */
public class Eg05cComboBoxExample {
        public static void main(String[] args) {
                EventQueue.invokeLater(() -> {
                        var frame = new Eg05cComboBoxFrame();
                        frame.setTitle("ComboBoxTest");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}
