package dvdrental.ui;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class Main {
        static {
                FlatIntelliJLaf.setup();
                /***
                 UIManager.put( "Component.arc", 0 );
                 UIManager.put("TableHeader.background", Color.DARK_GRAY);
                 UIManager.put("TableHeader.foreground", Color.WHITE);
                 UIManager.put("Table.foreground", Color.WHITE);
                 ***/
        }

        public static void main(String[] args) {
                EventQueue.invokeLater(() -> {
                        CustomerSearchForm frame = new CustomerSearchForm();
                        frame.setTitle("DVD Rental Store");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                });
        }
}