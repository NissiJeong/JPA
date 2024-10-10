package entityMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityMapping1 {
 
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Member member = new Member();
            member.setUserName("aaa");

            System.out.println("=================");
            em.persist(member);
            System.out.println("memeber.id = "+member.getId()); //jdbc 내부적으로 db에 insert 후에 해당 값을 바인딩 해주게 되어 있음.
            System.out.println("=================");
            
            tx.commit();
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }

        emf.close();
    }
}
