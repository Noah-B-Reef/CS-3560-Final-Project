package src.main.frontend;
import javax.swing.*; // Needed for Swing classes
import java.awt.event.*;

import entity.Student;
import jakarta.persistence.*;

public class StudentFrontend extends JFrame implements ActionListener{
    JFrame frame = new JFrame("StudentFrontend");
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    private JPanel nameAndButtons;
    private JTextField Name;
    private JPanel buttonBar;
    private JButton add;
    private JButton search;
    private JButton delete;
    private JButton update;
    private JTextField course;
    private JLabel nameLabel;
    private JLabel Course;
    private JPanel coursePanel;

    public StudentFrontend(){

//        buttonBar.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//            }

//        Name.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String name = Name.getText();
//                JOptionPane.showMessageDialog(null, "Your name is " + name);
//            }
//
//        });
//
//        course.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String course = Course.getText();
//                JOptionPane.showMessageDialog(null, "Course " + course);
//            }
//        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = Name.getText();
        String course = Course.getText();

        if (e.getSource() == add){
            transaction.begin();
            Student student = new Student("011111111", name, course);
        }
        else if (e.getSource() == update){

        }
        else if (e.getSource() == search){

        }
        else if (e.getSource() == delete){

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentFrontend");
        frame.setContentPane(new StudentFrontend().nameAndButtons);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
