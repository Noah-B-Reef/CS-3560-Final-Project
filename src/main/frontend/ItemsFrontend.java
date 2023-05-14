package src.main.frontend;
import entity.*;
import entity.Book;
import entity.Documentary;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateError;
import org.hibernate.dialect.PostgreSQLDialect;
import src.main.jdbc.ConnectionFactory;
import jakarta.persistence.*;
import javax.swing.*; // Needed for Swing classes
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Item;

public class ItemsFrontend extends JFrame{
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    JFrame frame = new JFrame("ItemsFrontend");
    private JButton addButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    private JLabel ItemCode;
    private JTextField itemType;
    private JComboBox<Boolean> status;
    private JComboBox<String> nameOfItem;
    private JTextField description;
    private JTextField location;
    public JPanel topLevelPanel;
    private JTextField dailyPrice;
    private JTextField txtTitle;
    private JTabbedPane tabbedPane1;
    private JPanel Book;
    private JRadioButton bookRadioButton;
    private JRadioButton documentaryRadioButton;
    private JComboBox authorCombo;
    private JTextField txtPages;
    private JTextField txtPublisher;
    private JTextField txtDate;
    private JComboBox directorCombo;
    private JTextField txtLength;
    private JTextField txtRelease;
    private JLabel Director;
    private JTextPane textPane1;


    public ItemsFrontend(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        // Add Books to Combobox
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> cr2 = cb.createQuery(Author.class);
        Root<Author> root2 = cr2.from(Author.class);
        cr2.select(root2);
        TypedQuery<Author> query2 = entityManager.createQuery(cr2);
        List<Author> authors = query2.getResultList();

        // removes all items currently in comboBox
        authorCombo.removeAllItems();

        for (int i = 0; i < authors.toArray().length; i++)
        {
            authorCombo.addItem(authors.get(i).getName());
        }

        status.addItem(true);
        status.addItem(false);

        // Add Documentaries to Combobox
        CriteriaBuilder cb3 = entityManager.getCriteriaBuilder();
        CriteriaQuery<Director> cr3 = cb3.createQuery(Director.class);
        Root<Director> root3 = cr3.from(Director.class);
        cr3.select(root3);
        TypedQuery<Director> query3 = entityManager.createQuery(cr3);
        List<Director> direcs = query3.getResultList();

        // removes all items currently in comboBox
        directorCombo.removeAllItems();

        for (int i = 0; i < direcs.toArray().length; i++)
        {
            directorCombo.addItem(direcs.get(i).getName());
        }

        ButtonGroup group = new ButtonGroup();
        group.add(bookRadioButton);
        group.add(documentaryRadioButton);


        addButton.addActionListener(new ItemsListener());
        searchButton.addActionListener(new ItemsListener());
        backButton.addActionListener(new ItemsListener());
        updateButton.addActionListener(new ItemsListener());
        deleteButton.addActionListener(new ItemsListener());


        //ShowItemValues();
    }


    public void ShowItemValues(){
        try{
            //          String query = "SELECT * from public.student WHERE id =?";
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM public.item");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                boolean [] checkedOut = {true, false};
                for (boolean b : checkedOut) {
                    status.addItem(b);
                }
                String itemName = rs.getString("title");
                nameOfItem.addItem(itemName);
            }
//            receiptItems.setText((String)receiptValues.getSelectedItem());

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);

        }

    }

    private class ItemsListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();

            // Add Books to Combobox
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Author> cr2 = cb.createQuery(Author.class);
            Root<Author> root2 = cr2.from(Author.class);
            cr2.select(root2);
            TypedQuery<Author> query2 = entityManager.createQuery(cr2);
            List<Author> authors = query2.getResultList();

            // Add Documentaries to Combobox
            CriteriaBuilder cb3 = entityManager.getCriteriaBuilder();
            CriteriaQuery<Director> cr3 = cb3.createQuery(Director.class);
            Root<Director> root3 = cr3.from(Director.class);
            cr3.select(root3);
            TypedQuery<Director> query3 = entityManager.createQuery(cr3);
            List<Director> direcs = query3.getResultList();


        if(e.getSource() ==addButton) {
            String code = itemType.getText();
            String title = txtTitle.getText();
            String descriptionText = description.getText() ;
            String locationVal = location.getText();
            // Redundant check, already set to true by default
            boolean checkedOutVal = (boolean)status.getSelectedItem();
            double daily_price = Double.parseDouble(dailyPrice.getText());
            Item itemInp = new Item(code, title, descriptionText, locationVal, daily_price);
            try {
//                Connection connection = ConnectionFactory.getConnection();
//
//
//                PreparedStatement stmt = connection.prepareStatement("INSERT INTO public.item(\n" +
//                        "\tcode, title, description, status, location, daily_price)\n" +
//                        "\tVALUES (?, ?, ?, ?, ?, ?)");
//                //since we have 4 parameters in this SQL statement, we need to pass each in one-by-one
//                stmt.setString(1, broncoID);
//                stmt.setString(2,name);
//                stmt.setString(3,courseVal);
//                stmt.setString(4,broncoID);
//
//                stmt.executeUpdate();



                transaction.begin();
                itemInp.setStatus((boolean) status.getSelectedItem());
                entityManager.persist(itemInp);
                transaction.commit();
                if (bookRadioButton.isSelected())
                {
                    Book book = new Book();
                    book.setItemByItemCode(itemInp);
                    book.setAuthorByAuthors(authors.get(authorCombo.getSelectedIndex()));
                    book.setPages(Integer.parseInt(txtPages.getText()));
                    book.setPublisher(txtPublisher.getText());
                    book.setPublicationDate(Date.valueOf(txtDate.getText()));
                    transaction.begin();
                    entityManager.persist(book);
                    transaction.commit();
                }
                else
                {
                    Documentary doc = new Documentary();
                    doc.setItemByItemCode(itemInp);
                    doc.setDirectorByDirector(direcs.get(directorCombo.getSelectedIndex()));
                    doc.setLength(Integer.parseInt(txtLength.getText()));
                    doc.setReleaseDate(Date.valueOf(txtDate.getText()));
                    transaction.begin();
                    entityManager.persist(doc);
                    transaction.commit();
                }



                JOptionPane.showMessageDialog(null, "Item code entered: " + code +
                        "\nName of item: " + title + "\nDescription: " + descriptionText + "\nChecked out? " + checkedOutVal + "\nLocated at: " + locationVal + "\nCost: $" + daily_price);


            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }else if (e.getSource() == updateButton) {
                String code = itemType.getText();
                String title = txtTitle.getText();
                String descriptionText = description.getText() ;
                String locationVal = location.getText();
                // Redundant check, already set to true by default
                boolean checkedOutVal = (boolean)status.getSelectedItem();
                double daily_price = Double.parseDouble(dailyPrice.getText());
                Item itemInp = new Item(code, title, descriptionText, locationVal, daily_price);
                try {
                    //making a connection to the db and seeing if a row is returned that has the values we want
                    Connection connection = ConnectionFactory.getConnection();
                    System.out.println("Updating Item " + code);
                    PreparedStatement stmt = connection.prepareStatement("UPDATE public.item\n" +
                            "\tSET code=?, title=?, description=?, status=?, location=?, daily_price=?\n" +
                            "\tWHERE code =?");
                    //since we have 4 parameters in this SQL statement, we need to pass each in one-by-one
                    stmt.setString(1, code);
                    stmt.setString(2,title);
                    stmt.setString(3,descriptionText);
                    stmt.setString(4,String.valueOf(checkedOutVal));
                    stmt.setString(5, code);

                    stmt.executeUpdate();
                    //autocommit is ON, so we don't need to use connection.commit();
                    JOptionPane.showMessageDialog(null, "Item code entered: " + code + "\nName of item: " + title + "\nDescription: " + descriptionText + "\nChecked out? " + checkedOutVal + "\nLocated at: " + locationVal + "\nCost: $" + daily_price);
                    System.out.println("Successfully updated item");


                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            } else if (e.getSource() == searchButton) {
                try {
                    // Add Customers to Combobox
                    CriteriaQuery<Book> cr = cb.createQuery(Book.class);
                    Root<Book> root = cr.from(Book.class);
                    cr.select(root);
                    TypedQuery<Book> query = entityManager.createQuery(cr);
                    List<Book> books = query.getResultList();
                    List<Object> titles = new ArrayList<Object>();

                    for (int i = 0; i < books.toArray().length; i++)
                    {
                        titles.add(books.get(i).toString());
                    }
                    CriteriaQuery<Documentary> cr1 = cb.createQuery(Documentary.class);
                    Root<Documentary> root1 = cr1.from(Documentary.class);
                    cr1.select(root1);
                    TypedQuery<Documentary> query1 = entityManager.createQuery(cr1);
                    List<Documentary> docs = query1.getResultList();
                    List<Object> docTitles = new ArrayList<Object>();
                    for (int i = 0; i < docs.toArray().length; i++)
                    {
                        docTitles.add(docs.get(i).toString());
                    }

                    Object[] options = {"Book",
                            "Documentary"};
                    int n = JOptionPane.showOptionDialog(frame,
                            "Select Item Type",
                            "Type Selection",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,     //do not use a custom Icon
                            options,  //the titles of buttons
                            options[0]); //default button title

                    if (n == 0)
                    {

                        String selection = (String)JOptionPane.showInputDialog(
                                frame,
                                "Select Book",
                                "Customized Dialog",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                titles.toArray(),
                                "ham");
                        int index = titles.indexOf(selection);

                        Book book = books.get(index);
                        itemType.setText(book.getItemByItemCode().getCode());
                        txtTitle.setText(book.getItemByItemCode().getTitle());
                        status.setSelectedItem(book.getItemByItemCode().isStatus());
                        description.setText(book.getItemByItemCode().getDescription());
                        location.setText(book.getItemByItemCode().getLocation());
                        dailyPrice.setText(String.valueOf(book.getItemByItemCode().getDailyPrice()));
                        authorCombo.setSelectedItem(book.getAuthorByAuthors());
                        txtPages.setText(String.valueOf(book.getPages()));
                        txtPublisher.setText(book.getPublisher());
                        txtDate.setText(book.getPublicationDate().toString());
                        bookRadioButton.setSelected(true);
                    }
                    else
                    {
                        String selection = (String)JOptionPane.showInputDialog(
                                frame,
                                "Select Documentary",
                                "Customized Dialog",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                docTitles.toArray(),
                                "ham");

                        int index = docTitles.indexOf(selection);
                        Documentary doc = docs.get(index);
                        itemType.setText(doc.getItemCode());
                        txtTitle.setText(doc.getItemByItemCode().getTitle());
                        status.setSelectedItem(doc.getItemByItemCode().isStatus());
                        description.setText(doc.getItemByItemCode().getDescription());
                        location.setText(doc.getItemByItemCode().getLocation());
                        dailyPrice.setText(String.valueOf(doc.getItemByItemCode().getDailyPrice()));
                        directorCombo.setSelectedItem(doc.getDirectorByDirector());
                        txtLength.setText(String.valueOf(doc.getLength()));
                        txtRelease.setText(doc.getReleaseDate().toString());
                        documentaryRadioButton.setSelected(true);
                    }




                    /*
                    //making a connection to the db and seeing if a row is returned that has the values we want
                    Connection connection = ConnectionFactory.getConnection();
                    System.out.println("Searching for item with code: " +itemInp.getCode());
                    //searching for student based off of their ID value
                    PreparedStatement stmt = connection.prepareStatement("SELECT code, title, description,status,location,daily_price\n" +
                            "\tFROM public.item WHERE code =?");

                    stmt.setString(1, code);

                    ResultSet rs = stmt.executeQuery();
                    //autocommit is ON, so we don't need to use connection.commit();
                    while(rs.next()) {
                        if (code.equals(rs.getString("code"))) {
                            JOptionPane.showMessageDialog(null, "Found " + rs.getString("code") + " within db!" +"\nSetting values to ones found in db");
                            System.out.println("Found " + rs.getString("code") + " within db!" + "\nSetting values to ones found in db");
                            //Name, course, id, use setText for each of these textfields
                            itemType.setText(rs.getString("code"));
                            description.setText(rs.getString("description"));
                            status.setSelectedItem(rs.getBoolean("status"));
                            nameOfItem.setSelectedItem(rs.getString("title"));
                            location.setText(rs.getString("location"));
                            dailyPrice.setText(String.valueOf(rs.getDouble("daily_price")));

                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to find student with ID:" + code + " within db!");
                        }
                    }
//                    private JTextField itemType;
//                    private JComboBox<Boolean> status;
//                    private JComboBox<String> nameOfItem;
//                    private JTextField description;
//                    private JTextField location;
//                    public JPanel topLevelPanel;
//                    private JTextField dailyPrice;

                     */
                }  catch (Exception err) {
                    JOptionPane.showMessageDialog(frame, "Entry not in database!");
                }

            } else if (e.getSource() == deleteButton) {
                try{
                    String item_code = itemType.getText();
                    if (bookRadioButton.isSelected())
                    {
                        Book book = entityManager.getReference(Book.class, item_code);
                        transaction.begin();
                        entityManager.remove(book);
                        entityManager.remove(book.getItemByItemCode());
                        transaction.commit();
                    }

                    else
                    {
                        Documentary doc = entityManager.getReference(Documentary.class, item_code);
                        transaction.begin();
                        entityManager.remove(doc);
                        entityManager.remove(doc.getItemByItemCode());
                        transaction.commit();
                    }

                    }catch (HibernateError err) {
                       JOptionPane.showMessageDialog(frame, "Unable to delete entry!");
                     }

            }
        else if (e.getSource() == backButton){
                WelcomeWindow welcomeInstance = new WelcomeWindow();
                welcomeInstance.setContentPane(welcomeInstance.revenuePanel);
                welcomeInstance.frame.setLocationRelativeTo(null);
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
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
