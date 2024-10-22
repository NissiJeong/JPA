package jpa_basic_2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class NPlus1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
 
        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Team teamB = new Team();
            team.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member4");
            member1.changeTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member5");
            member2.changeTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

            //fetchType = EAGER(즉시로딩) 의 경우 member를 리스트로 조회하면 그에 해당하는 team을 n번 조회하게 된다. N+1 문제
            //이것을 해결하기 위해서 다 지연로딩으로 세팅하고 아래 세가지 방법이 있다.
            //1. fetchJoin을 런타임에 내가 원하는 데이터만 가져온다.
            //2. 엔티티그래프 어노테이션으로 해결
            //3. 배치 사이즈로 해결(1+1)
            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();

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
