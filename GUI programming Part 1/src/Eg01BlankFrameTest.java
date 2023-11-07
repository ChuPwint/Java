import java.awt.*;
import javax.swing.*;

public class Eg01BlankFrameTest {
        public static void main(String[] args) {
                EventQueue.invokeLater(() ->
                {
                        JFrame frame = new BlankFrame();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}

class RunnableApp implements Runnable {

        @Override
        public void run() {
                JFrame frame = new BlankFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
        }
}

class BlankFrame extends JFrame {
        private static final int DEFAULT_WIDTH = 600;
        private static final int DEFAULT_HEIGHT = 300;

        public BlankFrame() {
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
}
