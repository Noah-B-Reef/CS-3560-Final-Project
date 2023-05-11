package src.main.frontend;
import javax.swing.*; // Needed for Swing classes
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import src.main.jdbc.ConnectionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Student;
import jakarta.persistence.*;
public class ItemsFrontend extends JFrame{
    JFrame frame = new JFrame("ItemsFrontend");
    private JButton addButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    private JLabel ItemCode;
    private JComboBox itemType;
    private JComboBox status;
    private JComboBox nameOfItem;
    private JTextField description;
    private JTextField location;
    public JPanel topLevelPanel;


    public ItemsFrontend(){



        addButton.addActionListener(new ItemsListener());
        searchButton.addActionListener(new ItemsListener());
        backButton.addActionListener(new ItemsListener());
        updateButton.addActionListener(new ItemsListener());
        deleteButton.addActionListener(new ItemsListener());
    }

    private class ItemsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        if(e.getSource() ==addButton){





        }
        else if(e.getSource() == updateButton){




        }
        else if(e.getSource() == deleteButton){





        }
        else if (e.getSource() == backButton){
                WelcomeWindow welcomeInstance = new WelcomeWindow();
                welcomeInstance.setContentPane(welcomeInstance.revenuePanel);

                welcomeInstance.pack();
                welcomeInstance.setVisible(true);
                //topLevelPanel.setVisible(false);

            }



            }
        }
    public static void main(String[] args) {
        JFrame frame = new JFrame("ItemsFrontend");
        frame.setContentPane(new ItemsFrontend().topLevelPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
