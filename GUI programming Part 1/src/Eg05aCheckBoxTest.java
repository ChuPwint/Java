/**
 * @version 1.32 2004-05-05
 * @author Cay Horstmann
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Eg05aCheckBoxTest {
        public static void main(String[] args) {
                EventQueue.invokeLater(() ->
                {
                        CheckBoxFrame frame = new CheckBoxFrame();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}

/**
 * A frame with a sample text label and check boxes for
 * selecting font attributes.
 */
class CheckBoxFrame extends JFrame {
        public static final int DEFAULT_WIDTH = 300;
        public static final int DEFAULT_HEIGHT = 200;

        private JLabel label;
        private JCheckBox bold;
        private JCheckBox italic;

        private static final int FONTSIZE = 12;

        public CheckBoxFrame() {
                setTitle("CheckBoxTest");
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

                // add the sample text label

                label = new JLabel("The quick brown fox jumps over the lazy dog.");
                label.setFont(new Font("Serif", Font.PLAIN, FONTSIZE));
                add(label, BorderLayout.NORTH);

                // this listener sets the font attribute of
                // the label to the check box state

                ActionListener listener = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                                int mode = 0;
                                if (bold.isSelected()) mode += Font.BOLD;
                                if (italic.isSelected()) mode += Font.ITALIC;
                                label.setFont(new Font("Serif", mode, FONTSIZE));
                        }
                };

                // add the checkboxes

                JPanel buttonPanel = new JPanel();

                bold = new JCheckBox("Bold");
                bold.addActionListener(listener);
                buttonPanel.add(bold);

                italic = new JCheckBox("Italic");
                italic.addActionListener(listener);
                buttonPanel.add(italic);

                add(buttonPanel, BorderLayout.SOUTH);
        }

}

