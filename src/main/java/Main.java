import jakarta.persistence.*;
import entity.*;
import java.sql.Date;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();

            Item item = new Item("2451LS", "Harry Potter", "Wizards", "Hogwarts", 12.50);

            Date date = Date.valueOf("2001-06-19");
            String authors = "J.K. Rowling";
            Book harry = new Book(authors, 13, "HP Publishing", date);
            harry.setItemByItemCode(item);
            entityManager.persist(harry);
            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
