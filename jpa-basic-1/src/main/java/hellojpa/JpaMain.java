package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory 객체는 어플리케이션이 실행될 때 한번 생성
        // 웹 서버가 올라오는 시점에 딱 하나 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 트랜잭션 단위마다 생성 종료
        // 고객의 요청이 올 때마다 생성했다가 버렸다가 함
        // 쓰레드 간에 절대 공유하면 안됨. 그렇게 설계 한다면 장애가 발생. 데이터 베이스 커넥션처럼 사용하고 반납
        EntityManager em = emf.createEntityManager();
        
        // Tranaction 이 중요함
        // JPA의 모든 변경은 트랜잭션 안에서 실행 해야 함
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();

        try{
            /*
            //생성
            Member member = new Member();
            member.setId(2L);
            member.setName("B");
            em.persist(member);
             */

            //조회
            Member2 findMember = em.find(Member2.class, 1L);
            System.out.println("findMember: "+findMember.getId());
    
            //삭제
            //em.remove(findMember);

            //수정 - 따로 저장하지 않고 entity에 setName만 해주면 된다.
            //자바 collection을 다룬것 처럼 설계가 되어 있다.
            //JPA를 통해서 Entity를 가져오면 JPA가 관리, JPA가 변경 됐는지 안됐는지 트랜잭션 커밋한 시점에 확인. 변경이 되어있으면 update 쿼리 작성.
            findMember.setName("Hello JPA");

            //jpql은 복잡한 쿼리, 통계 쿼리 등 직접 쿼리를 작성해서 쿼리 명령어를 실행시킬 수 있음. 
            //jpql은 테이블 대상으로 코드를 짜지 않음. 멤버 객체를 대상으로 코드를 작성. Member 엔티티 객체를 대상으로 
            //아래는 실제 실행 쿼리
            // /* select
            //     m 
            // from
            //     Member as m */ select
            //     member0_.id as id1_0_,
            //     member0_.name as name2_0_ 
            // from
            //     Member member0_
            List<Member2> result = em.createQuery("select m from Member2 as m", Member2.class).getResultList();
            //jpql은 어떤 메리트가 있나?
            //페이징을 하거나 setFirstResult, setMaxResult 등으로 하면 자동을 반영 됨. 
            //jpql은 객체를 대상으로 하는 객체지향 쿼리, 내가 선택한 database에 맞춰서 알아서 변경을 해 줌.

            /*
             * 정리!
             * 테이블이 아닌 객체를 대상으로 검색하는 객체지향 
             * SQL을 추상화해서 특정 데이터 베이스 SQL에 의존하지 않음.
             * JPQL을 한마디로 정의하면 객체지향 SQL
             */

            for(Member2 member : result ){
                System.out.println("member: "+member.getName());
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{
            em.close();
            emf.close();
        }

       
        
        
    }
}
