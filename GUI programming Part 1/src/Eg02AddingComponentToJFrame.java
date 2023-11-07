import javax.swing.*;
import java.awt.*;

public class Eg02AddingComponentToJFrame {
        public static void main(String[] args) {
                EventQueue.invokeLater(() ->
                {
                        JFrame frame = new MyFrameEg02();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}

class MyFrameEg02 extends JFrame {
        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 200;
        private static final String SHOW = "Show";
        private static final String HIDE = "Hide";

        private JPanel pnl;
        private JButton showOrHide;

        public MyFrameEg02() {
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                // JPanel is used to host other ui elements
                pnl = new JPanel();
                showOrHide = new JButton(SHOW);
                pnl.add(showOrHide);
                this.add(pnl);
        }
}
