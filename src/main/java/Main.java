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
            entityManager.merge(item);
            Date date = new Date(2001,6,19);
            Book harry = new Book(new String[]{"J.K. Rowling"}, 13, "HP Publishing", date);
            harry.setItemByItemCode(item);
            entityManager.merge(harry);
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
