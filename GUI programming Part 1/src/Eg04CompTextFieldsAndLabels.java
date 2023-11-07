import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Eg04CompTextFieldsAndLabels {
        //static { FlatDarkLaf.setup(); }
        public static void main(String[] args) {
                EventQueue.invokeLater(() ->
                {
                        JFrame frame = new LoginFrame();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}

class LoginFrame extends JFrame {

        private static final int DEFAULT_WIDTH = 500;
        private static final int DEFAULT_HEIGHT = 300;

        public LoginFrame() {
                setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

                JTextField nameTxf = new JTextField();
                JPasswordField pwdTxf = new JPasswordField();
                JPanel topPnl = new JPanel();
                GridLayout gridLayout = new GridLayout();
                gridLayout.setRows(4);
                gridLayout.setColumns(2);
                //gridLayout.setHgap(8);
                //gridLayout.setVgap(8);
                topPnl.setLayout(gridLayout);
                // Label တွေကို grid cell ရဲ့ ညာဘက်ကပ်အောင် SwingConstants.RIGHT သုံးတာ
                topPnl.add(new JLabel("User name: ", SwingConstants.RIGHT));
                topPnl.add(nameTxf);
                topPnl.add(new JLabel("Password: ", SwingConstants.RIGHT));
                topPnl.add(pwdTxf);

                JButton loginBtn = new JButton("Login");
                JButton regBtn = new JButton("Register");
                // GridLayout cell တွေကို အလွတ်ထားလို့မရဘူး၊ မသုံးရင် panel တစ်ခုထည့်ထားလို့ရတယ်
                topPnl.add(new JPanel()); // (row 2, col 0) မှာ လွတ်နေချင်တာ
                topPnl.add(loginBtn);
                topPnl.add(new JPanel()); // (row 3, co0) မှာ လွတ်နေချင်တာ
                topPnl.add(regBtn);

                //  topPnl ကို JFrame ရဲ့ အပေါ်ပိုင်းမှာ ပေါ်အောင် BorderLayout.NORTH
                // JFrame ရဲ့ default layout က BorderLayout
                this.add(topPnl, BorderLayout.NORTH);

                JLabel resultLbl = new JLabel();
                resultLbl.setText("");

                loginBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                if (Eg04LoginService.loginWith(nameTxf.getText(),
                                                new String(pwdTxf.getPassword()))) {
                                        resultLbl.setText("Successful!");
                                } else {
                                        resultLbl.setText("Sorry... user name or password is wrong!");
                                }
                        }

                }
                );

                add(resultLbl, BorderLayout.SOUTH);
        }
}

class Eg04LoginService {
        public static Map<String, String> credentials;

        static {
                credentials = new HashMap<>();
                credentials.put("John", "121");
                credentials.put("Joe", "122");
                credentials.put("Kathy", "123");
        }

        public static boolean loginWith(String usr, String pwd) {
                for(Map.Entry<String,String> ent : credentials.entrySet()) {
                        if (ent.getKey().equals(usr)
                                        && ent.getValue().equals(pwd)) return true;
                }
                return false;
        }
}
