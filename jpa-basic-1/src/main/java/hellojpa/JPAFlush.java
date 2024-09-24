package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAFlush {

    public static void main(String[] args) {
        // EntityManagerFactory는 AutoClosable 인터페이스를 implement 하지 않아서 try-with-resources 를 사용할 수 없음.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            // em.flush() flush 직접 호출
            // directJPAFlush(em, tx);

            detachedPersistenceContext(em, tx);
        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }

        emf.close();
    }

    // em.flush() flush 직접 호출
    static void directJPAFlush(EntityManager em, EntityTransaction tx){
        Member2 member = new Member2(200L, "200L");
        em.persist(member);

        em.flush();

        System.out.println("========================");

        tx.commit();
    }

    static void detachedPersistenceContext(EntityManager em, EntityTransaction tx){
        //select 쿼리로 데이터를 가져온 후 member 객체를 영속성 컨텍스트 1차 캐시에 저장해 두어서 현재는 영속 상태이다.
        Member2 findMember = em.find(Member2.class, 160L);
        findMember.setName("ZZZZ");

        // 이렇게 detace하면 준영속 상태가 되며 기존에 dirty Checking 을 자동으로 했지만 영속성 컨텍스트가 더이상 관리하지 않기 때문에 dirty checking 하지 않는다.
        em.detach(findMember);

        //em.clear();
        //em.close();

        Member2 findMember1 = em.find(Member2.class, 160L);

        System.out.println("==============");
        tx.commit();
    }
}
