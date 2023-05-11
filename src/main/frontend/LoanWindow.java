package src.main.frontend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import src.main.jdbc.ConnectionFactory;

public class LoanWindow {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    JFrame frame = new JFrame("LoanWindow");
    private JButton backButton;
    private JTextField loanNumber;
    private JTextField studentValue;
    private JTextField originationDate;
    private JTextField dueDate;
    private JPanel loanFieldsPanel;
    private JButton addButton;
    private JButton updateButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton viewCurrentLoansButton;
    private JPanel buttonPanel;
    public JPanel fullPanel;
    private JTextField itemCode;
    private JTextField studentIDs;
    private JLabel Student;


    public LoanWindow(){
        addButton.addActionListener(new LoanWindowButtons());
        updateButton.addActionListener(new LoanWindowButtons());
        searchButton.addActionListener(new LoanWindowButtons());
        deleteButton.addActionListener(new LoanWindowButtons());
        viewCurrentLoansButton.addActionListener(new LoanWindowButtons());
        backButton.addActionListener(new LoanWindowButtons());

//        FillStudentIDs();
    }

//    public void FillStudentIDs(){
//        try{
////          String query = "SELECT * from public.student WHERE id =?";
//            Connection connection = ConnectionFactory.getConnection();
//            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM public.student");
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()){
//                String studentIDVal = rs.getString("id");
//                studentIDs.addItem(studentIDVal);
//            }
//
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(null,e);
//
//        }
//
//    }

    private class LoanWindowButtons implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
//            try {
//                int number = Integer.parseInt(loanNumber.getText());
//            }catch(NumberFormatException ex1){
//                throw new RuntimeException(ex1);
//
//            }
            int number = Integer.parseInt(loanNumber.getText());
            String bronco_id = studentIDs.getText();
            String itemCodeVal = itemCode.getText();

            //parsing string->java.util.Date->java.sql.Date
            java.util.Date java_due_date;
            try {
                java_due_date = new SimpleDateFormat("dd/MM/yyyy").parse(dueDate.getText());
                java.sql.Date due_date = new java.sql.Date(java_due_date.getTime());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            java.util.Date java_origination_date;
            try {
                java_origination_date = new SimpleDateFormat("dd/MM/yyyy").parse(originationDate.getText());
                java.sql.Date origination_date = new java.sql.Date(java_due_date.getTime());

            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            //pass in required parameters so entitymanager can do its operations properly
            //constructor for entity in Loan class is empty
            //Loan loanInstance = new Loan(number, origination_date, due_date, bronco_id, itemCodeVal);
            if (e.getSource() == addButton) {
                ReceiptWindow receiptWindowInstance = new ReceiptWindow();
                receiptWindowInstance.frame.setContentPane(receiptWindowInstance.containerPanel);
                receiptWindowInstance.frame.setLocationRelativeTo(null);
                receiptWindowInstance.frame.pack();
                receiptWindowInstance.frame.setVisible(true);
                try {
                    //making a connection to the db and seeing if a row is returned that has the values we want
                    Connection connection = ConnectionFactory.getConnection();
                    PreparedStatement stmt = connection.prepareStatement("INSERT INTO public.\"Loan\"" +
                            "(\"number\", due_date, bronco_id, item_code, date)" +
                            "\nVALUES (?, ?, ?, ?, ?)");
                    stmt.setInt(1, number);
                    stmt.setString(2,"02/01/2023");
                    stmt.setString(3, bronco_id);
                    stmt.setString(4,itemCodeVal);
                    stmt.setString(5,"01/01/2023");
                    stmt.executeUpdate();
                } catch (ClassNotFoundException e2) {
                    throw new RuntimeException(e2);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Loan number: " + number + "\nDate: " +
                        "\n");

            }
            if (e.getSource() == updateButton) {
                  try{
                      Connection connection = ConnectionFactory.getConnection();
                      PreparedStatement stmt = connection.prepareStatement("UPDATE public.\"Loan\"\n" +
                                        "SET \"number\"=?, due_date=?, bronco_id=?, item_code=?, date=?\n" +
                                        "WHERE id =?");

                      stmt.setInt(1, number);
                      stmt.setString(2,originationDate.getText());
                      stmt.setString(3, bronco_id);
                      stmt.setString(4,itemCodeVal);
                      stmt.setString(5,dueDate.getText());
                      stmt.executeUpdate();
                      JOptionPane.showMessageDialog(null, "Successfully updated values for Loans table");
                  } catch(ClassNotFoundException ex0){
                      throw new RuntimeException(ex0);

                  }catch(SQLException ex0){
                      throw new RuntimeException(ex0);
                  }

//                UPDATE public."Loan"
//                SET "number"=?, due_date=?, bronco_id=?, item_code=?, date=?
//                WHERE <condition>;
            }
            if (e.getSource() == searchButton) {
                try {
                    //making a connection to the db and seeing if a row is returned that has the values we want
                    Connection connection = ConnectionFactory.getConnection();
                    String optionPaneID = JOptionPane.showInputDialog("Enter ID to search for: ");
                    System.out.println("Searching for " +optionPaneID);
                    //searching for student based off of their ID value
                    PreparedStatement stmt = connection.prepareStatement("SELECT *\n" +
                            "\tFROM public.\"Loan\" WHERE id =?");

                    stmt.setString(1, optionPaneID);

                    ResultSet rs = stmt.executeQuery();
                    //autocommit is ON, so we don't need to use connection.commit();
                    while(rs.next()) {
                        if (optionPaneID.equals(rs.getString("bronco_id"))) {
                            JOptionPane.showMessageDialog(null, "Found " + rs.getString("bronco_id") + " within db!"+ "\nSetting values to ones found in db");
                            System.out.println("Found " + rs.getString("bronco_id") + " within db!" );
                            //Name, course, id, use setText for each of these textfields
                            studentIDs.setText(rs.getString("bronco_id"));
                            itemCode.setText(rs.getString("item_code"));
                            originationDate.setText(rs.getString("date"));
                            dueDate.setText(rs.getString("due_date"));

                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to find student with ID:" + optionPaneID + " within db!");
                        }
                    }


                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                //SELECT "number", due_date, bronco_id, item_code, date
                //	FROM public."Loan";
            }
            if (e.getSource() == deleteButton) {

                try {
                    //making a connection to the db and seeing if a row is returned that has the values we want
                    Connection connection = ConnectionFactory.getConnection();
                    System.out.println("Deleting Student");
                    //deleting student based on whether or not id matches input
                    PreparedStatement stmt = connection.prepareStatement("DELETE FROM public.\"Loan\"\n" +
                            "\tWHERE bronco_id= ?");
//                    stmt.setInt(1, Integer.parseInt(broncoID));
                    stmt.setString(1, bronco_id);

                    stmt.executeUpdate();
                    //autocommit is ON, so we don't need to use connection.commit();
                    JOptionPane.showMessageDialog(null,"Successfully deleted values from Loans table");


                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
//                DELETE FROM public."Loan"
//	            WHERE <condition>;
            }
            if (e.getSource() == viewCurrentLoansButton) {
                JOptionPane.showMessageDialog(null,"This feature is not available at the moment. Please try again later." + JOptionPane.ERROR_MESSAGE);
            }
            if (e.getSource() == backButton) {
                WelcomeWindow welcomeInstance = new WelcomeWindow();
                welcomeInstance.frame.setContentPane(welcomeInstance.revenuePanel);
//                welcomeInstance.frame.setDefaultCloseOperation(welcomeInstance.frame.EXIT_ON_CLOSE);
                welcomeInstance.frame.setLocationRelativeTo(null);
                welcomeInstance.frame.pack();
                welcomeInstance.frame.setVisible(true);
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("LoanWindow");
        frame.setContentPane(new LoanWindow().fullPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
