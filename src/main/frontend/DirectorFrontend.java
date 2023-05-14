package src.main.frontend;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import src.main.jdbc.ConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectorFrontend {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    JFrame frame = new JFrame("DirectorFrontend");
    private JTextField nameTextField;
    private JTextField nationalityTextField;
    private JTextField styleTextField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JLabel nameLabel;
    private JLabel nationalityLabel;
    private JLabel styleLabel;
    private JButton searchButton;

    public DirectorFrontend() {
        // Create the text fields
        nameTextField = new JTextField(20);
        nationalityTextField = new JTextField(20);
        styleTextField = new JTextField(20);

        // Create a panel to hold the components
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(nationalityLabel);
        panel.add(nationalityTextField);
        panel.add(styleLabel);
        panel.add(styleTextField);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
//        addButton.addActionListener(new ButtonsAndTextField());
        buttonPanel.add(updateButton);
//        updateButton.addActionListener(new ButtonsAndTextField());
        buttonPanel.add(deleteButton);
//        deleteButton.addActionListener(new ButtonsAndTextField());
        buttonPanel.add(searchButton);
//        searchButton.addActionListener(new ButtonsAndTextField());

        // Create a frame to hold the panels
        JFrame frame = new JFrame("DirectorFrontend");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

//    private class ButtonsAndTextField implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String name = nameTextField.getText();
//            String nationality = nationalityTextField.getText();
//            String style = styleTextField.getText();
//
//            if (e.getSource() == addButton) {
//
//                transaction.begin();
//                transaction.commit();
//
//                JOptionPane.showMessageDialog(null, "Name entered: " + name +
//                        "\nAnd your course entered is: " + courseVal + "\nBronco ID: " + broncoID);
//
//            } else if (e.getSource() == updateButton) {
//                try {
//                    //making a connection to the db and seeing if a row is returned that has the values we want
//                    Connection connection = ConnectionFactory.getConnection();
//                    System.out.println("Updating student " +studentInp.getName());
//                    PreparedStatement stmt = connection.prepareStatement("UPDATE public.student " +
//                            "SET id=?, name=?, course=? WHERE id = ?");
//                    //since we have 4 parameters in this SQL statement, we need to pass each in one-by-one
//                    stmt.setString(1, broncoID);
//                    stmt.setString(2,name);
//                    stmt.setString(3,courseVal);
//                    stmt.setString(4,broncoID);
//
//                    stmt.executeUpdate();
//                    //autocommit is ON, so we don't need to use connection.commit();
//                    System.out.println("Successfully updated student");
//
//
//                } catch (ClassNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            } else if (e.getSource() == searchButton) {
//                try {
//                    //making a connection to the db and seeing if a row is returned that has the values we want
//                    Connection connection = ConnectionFactory.getConnection();
//                    System.out.println("Searching for " +studentInp.getName());
//                    //searching for student based off of their ID value
//                    PreparedStatement stmt = connection.prepareStatement("SELECT id, name, course\n" +
//                            "\tFROM public.student WHERE id =?");
//
//                    stmt.setString(1, broncoID);
//
//                    ResultSet rs = stmt.executeQuery();
//                    //autocommit is ON, so we don't need to use connection.commit();
//                    while(rs.next()) {
//                        if (broncoID.equals(rs.getString("id"))) {
//                            JOptionPane.showMessageDialog(null, "Found " + rs.getString("name") + " within db!");
//                            System.out.println("Found " + rs.getString("name") + " within db!" + "\nSetting values to ones found in db");
//                            //Name, course, id, use setText for each of these textfields
//                            Name.setText(rs.getString("name"));
//                            course.setText(rs.getString("course"));
//                            id.setText(rs.getString("id"));
//
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Failed to find student with ID:" + broncoID + " within db!");
//                        }
//                    }
//
//
//                } catch (ClassNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            } else if (e.getSource() == deleteButton) {
//                try {
//                    //making a connection to the db and seeing if a row is returned that has the values we want
//                    Connection connection = ConnectionFactory.getConnection();
//                    System.out.println("Deleting Student " +studentInp.getName());
////                    SessionFactory factory = new Configuration().configure("persistence.xml").addAnnotatedClass(Student.class).buildSessionFactory();
//                    //deleting student based on whether or not id matches input
//                    PreparedStatement stmt = connection.prepareStatement("DELETE FROM public.student\n" +
//                            "\tWHERE id= ?");
////                    stmt.setInt(1, Integer.parseInt(broncoID));
//                    stmt.setString(1, broncoID);
//
//                    stmt.executeUpdate();
//                    //autocommit is ON, so we don't need to use connection.commit();
//                    System.out.println("Successfully deleted student");
//
//
//                } catch (ClassNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            } else if (e.getSource() == backButton) {
//                WelcomeWindow welcomeInstance = new WelcomeWindow();
//                welcomeInstance.frame.setContentPane(welcomeInstance.revenuePanel);
//                welcomeInstance.frame.setLocationRelativeTo(null);
//                welcomeInstance.frame.pack();
//                welcomeInstance.frame.setVisible(true);
//
//
//            }
//        }
//    }
    public static void main(String[] args) {
        DirectorFrontend frontend = new DirectorFrontend();
    }

    public void setVisible(boolean b) {
    }
}
