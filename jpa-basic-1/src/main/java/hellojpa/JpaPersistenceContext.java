package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entityMapping.Member;

public class JpaPersistenceContext {

    /*
    영속성 관리
    JPA에서 가장 중요한 2가지
    1. 객체와 관계형 데이터 베이스 매핑하기(설계)
    2. 영속성 컨텍스트(실제 내부적으로 동작하는 부분)

    영속성 컨텍스트를 이해하기 위해서는 먼저
    엔티티 매니저 팩토리와 엔티티 매니저에 대해 이해해야 한다.
    웹 어플리케이션을 개발한다고 할 때
    엔티티 매니저 팩토리를 통해서 고객의 요청이 올 때 마다 엔티티 메니저를 생성한다. 엔티티 메니저는 내부적으로 데이터베이스 커넥션을 사용해서 db를 사용 

    !영속성 컨텍스트
    JPA를 이해하는데 가장 중요한 용어
    "엔티티를 영구 저장하는 환경" 이라는 뜻
    EntityManager.persist(entity);
    결국 디비에 저장한다는 뜻이라기 보다는 영속성 컨텍스트를 통해서 엔티티를 영속화한다. 엔티티를 영속성 컨텍스트에 저장한다.

    영속성 컨텍스트는 논리적인 개념이고 눈에 보이지 않는다. 엔티티 매니저를 통해 영속성 컨텍스트에 접근

    entity manager를 생성하면 1:1로 영속성 컨텍스트가 생성 됨.
    entity manager 안에 눈에 보이지 않는 공간이 생긴다고 보면 됨.
    
    엔티티는 생명 주기가 있음
    비영속(new/transient): 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태, entitymanger.persist하면 영속 상태가 됨.
    영속(managed): 영속성 컨텍스트에 의해 관리되는 상태
    준영속(detached): 영속성 컨텍스트에 저장되었다가 분리된 상태
    삭제(removed): 삭제된 상태

    코드로 엔티티의 생명주기를 보면 이해하기 쉬운데,
    Member member = new Member();
    member.setId("A");
    member.setUserName("회원1");

    아래처럼
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    // 객체를 저장한 상태(영속)
    em.persist(member);
     */

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();

        try{
            /*
            Member member = new Member();
            member.setId(101L);
            member.setName("Hello Persistence Context");

            em.persist(member);
            */

            //Member findMember1 = em.find(Member.class, 101L);
            //Member findMember2 = em.find(Member.class, 101L);

            //System.out.println("Member: "+findMember1.getName());

            // 엔티티의 동일성 보장
            //entityIdentityCompare(em, tx);

            // 트랜잭션을 지원하는 쓰기 지연 transactional write-behind
            //transactionalWriteBehind(em, tx);

            // 변경 감지(Dirty Checking)
            dirtyChecking(em, tx);
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            em.close();
            emf.close();
        }
    }

    // 엔티티의 동일성 보장
    static void entityIdentityCompare(EntityManager em, EntityTransaction tx){

        Member findMember1 = em.find(Member.class, 101L);
        Member findMember2 = em.find(Member.class, 101L);

        System.out.println("result = "+(findMember1 == findMember2));

        tx.commit();
    }

    // 트랜잭션을 지원하는 쓰기 지연 transactional write-behind
    static void transactionalWriteBehind(EntityManager em, EntityTransaction tx){
        Member2 member1 = new Member2(160L, "A");
        Member2 member2 = new Member2(170L, "A");

        em.persist(member1);
        em.persist(member2);

        System.out.println("================================");

        tx.commit();
    }

    // 변경 감지(Dirty Checking)
    static void dirtyChecking(EntityManager em, EntityTransaction tx){
        Member2 findMember = em.find(Member2.class, 160L);
        findMember.setName("ZZZZ");

        System.out.println("=====================");
        tx.commit();
    }
}
