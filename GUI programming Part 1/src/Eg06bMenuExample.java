import java.awt.*;
import javax.swing.*;

/**
 * @author Cay Horstmann
 * @version 1.25 2018-04-10
 */
public class Eg06bMenuExample {
        public static void main(String[] args) {
                EventQueue.invokeLater(() -> {
                        var frame = new Eg06bMenuFrame();
                        frame.setTitle("MenuTest");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}
