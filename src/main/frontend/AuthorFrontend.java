package src.main.frontend;

import javax.swing.*;
import java.awt.*;

public class AuthorFrontend {
    private JTextField nameTextField;
    private JTextField nationalityTextField;
    private JTextField subjectTextField;
    private JButton addButton;
    private JButton updateButton;
    private JButton searchButton;
    private JLabel nameLabel;
    private JLabel nationalityLabel;
    private JLabel subjectLabel;
    private JButton deleteButton;

    public AuthorFrontend() {
        // Create the text fields
        nameTextField = new JTextField(20);
        nationalityTextField = new JTextField(20);
        subjectTextField = new JTextField(20);

        // Create a panel to hold the components
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(nationalityLabel);
        panel.add(nationalityTextField);
        panel.add(subjectLabel);
        panel.add(subjectTextField);

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
        JFrame frame = new JFrame("AuthorFrontend");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        AuthorFrontend frontend = new AuthorFrontend();
    }

    public void setVisible(boolean b) {
    }
}
