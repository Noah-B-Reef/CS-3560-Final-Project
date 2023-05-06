import entity.Student;
import jakarta.persistence.*;

public class Main {
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            Student student = new Student();
            student.setId("014000415");
            student.setName("Noah Reef");
            student.setCourse("OOAD");
            entityManager.merge(student);
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
