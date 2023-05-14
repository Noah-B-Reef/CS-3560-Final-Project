package src.main.frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PeopleOpsWindow {
    JFrame frame = new JFrame("PeopleOpsWindow");
    private JButton studentButton;
    private JButton authorButton;
    private JButton directorButton;

    public PeopleOpsWindow() {
        // Add an ActionListener to each button
        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                openNewWindow("studentButton Clicked");
                StudentFrontend studentWindow = new StudentFrontend();
                studentWindow.frame.setContentPane(new StudentFrontend().nameAndButtons);
                studentWindow.frame.setLocationRelativeTo(null);
                studentWindow.frame.pack();
                studentWindow.frame.setVisible(true);
            }
        });
        directorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DirectorFrontend directorFrontend = new DirectorFrontend();
                directorFrontend.setVisible(true);
            }
        });
        authorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AuthorFrontend authorFrontend = new AuthorFrontend();
                authorFrontend.setVisible(true);
            }
        });

        // Set the layout manager of the content pane to GridLayout
        frame.getContentPane().setLayout(new GridLayout(1, 0));

        // Add the buttons to the frame
        frame.getContentPane().add(studentButton);
        frame.getContentPane().add(authorButton);
        frame.getContentPane().add(directorButton);

        // Set the properties of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void openNewWindow(String windowTitle) {
        // Close the current window
        frame.dispose();

        // Create a new JFrame
        JFrame newFrame = new JFrame(windowTitle);

        // Set the properties of the new frame
        newFrame.setSize(300, 200);
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
    }

    public static void main(String[] args) {
        PeopleOpsWindow window = new PeopleOpsWindow();
    }
}
