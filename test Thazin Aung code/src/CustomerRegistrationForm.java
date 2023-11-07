import javax.swing.*;
import java.awt.*;

public class CustomerRegistrationForm {

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            JFrame frame = new CustomerAdding();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}