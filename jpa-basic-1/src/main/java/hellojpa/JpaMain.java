package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory 객체는 어플리케이션이 실행될 때 한번 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 트랜잭션 단위마다 생성 종료
        EntityManager em = emf.createEntityManager();
        
        // Tranaction 이 중요함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setId(2L);
            member.setName("B");
            em.persist(member);
    
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
            emf.close();
        }

       
        
        
    }
}
