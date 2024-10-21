package jpa_basic_2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProxyMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
 
        try{
            
            /*
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());

            System.out.println("m1.getClass == m2.getClass "+(m1.getClass() == m2.getClass()));

            Member rm1 = em.getReference(Member.class, member1.getId());
            System.out.println("rm1 : "+rm1.getClass());

            // 한 트랜잭션 안(같은 영속성 컨텍스트) 에 같은 pk로 조회한 Entity는 같은 값인 것을 보장해준다.
            System.out.println("m1 == rm1 : "+ (m1 == rm1));
             */

            /* 
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());

            Member findMember = em.find(Member.class, member1.getId()); //위에서 같은 pk로 Proxy로 조회해왔기 때문에 여기서도 proxy 로 조회해 온다.
            System.out.println("findMember =" + findMember.getClass());

            // JPA 에서는 == 비교 true 를 보장해준다.
            System.out.println("refMember == findMember : "+ (refMember == findMember));
            */

            Member member1 = new Member();
            member1.setUsername("member3");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());

            em.detach(refMember);
            //em.close();
            
            // 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기화 시키면 문제 발생(하이버네이트는 org.hibernate.LazyInitializationExeption 예외를 발생시킴)
            System.out.println("refMember.getUsername: "+refMember.getUsername());

            tx.commit();
        } catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally{
            em.close();
        }

        emf.close();
    }
}
