import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eg03cAddingListener {
        public static void main(String[] args) {
                EventQueue.invokeLater(() ->
                {
                        JFrame frame = new MyFrameEg03c();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}

class MyFrameEg03c extends JFrame {
        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 200;
        private static final String SHOW = "Show";
        private static final String HIDE = "Hide";

        private JPanel pnl;
        private JButton showOrHide;
        JLabel secretLblTxt;

        public MyFrameEg03c() {
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                pnl = new JPanel();
                showOrHide = new JButton(SHOW);
                secretLblTxt = new JLabel("This is highly sensitive secret!");
                secretLblTxt.setVisible(false);

                // Lambda Expression
                showOrHide.addActionListener((ActionEvent e) -> { reverseShowOrHide();});

                pnl.add(showOrHide);
                pnl.add(secretLblTxt);
                add(pnl);
        }

        private void reverseShowOrHide() {
                if (SHOW.equals(showOrHide.getText())) {
                        secretLblTxt.setVisible(true);
                        showOrHide.setText(HIDE);
                        System.out.println("reverseShowOrHide");
                } else {
                        secretLblTxt.setVisible(false);
                        showOrHide.setText(SHOW);

                }
                // pnl.revalidate();
                pnl.repaint();
        }
}
