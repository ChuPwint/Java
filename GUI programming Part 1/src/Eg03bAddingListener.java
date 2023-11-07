import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eg03bAddingListener {
        public static void main(String[] args) {
                EventQueue.invokeLater(() ->
                {
                        JFrame frame = new MyFrameEg03b();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}

class MyFrameEg03b extends JFrame {
        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 200;
        private static final String SHOW = "Show";
        private static final String HIDE = "Hide";

        private JPanel pnl;
        private JButton showOrHide;
        JLabel secretLblTxt;

        public MyFrameEg03b() {
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                pnl = new JPanel();
                showOrHide = new JButton(SHOW);
                secretLblTxt = new JLabel("This is highly sensitive secret!");
                secretLblTxt.setVisible(false);

                // showOrHide button အတွက် action listener တစ်ခုကို register လုပ်တာ
                // lambda expression မသုံးပဲဆိုရင် ဒီလို ရေးရမယ်
                class SomeAction implements ActionListener {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                reverseShowOrHide();
                        }
                }
                showOrHide.addActionListener(new SomeAction());

                pnl.add(showOrHide);
                pnl.add(secretLblTxt);
                add(pnl);
        }

        private void reverseShowOrHide() {
                System.out.println("reverseShowOrHide");
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
