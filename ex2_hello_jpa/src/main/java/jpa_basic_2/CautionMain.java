package jpa_basic_2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CautionMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);
            // --> 여기까지가 jpa 관점에서 맞는 코드이지만
            // 순수한 객체 관계를 고려 & 테스트 케이스를 생각하면 양쪽 다 값을 입력 해야 한다.

            // team.getMembers().add(member);

            //em.flush();
            //em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("=======================");
            for(Member m : members){
                System.out.println("m = "+m);
            }
            System.out.println("=======================");

            /*
            아래의 코드는 연관관계의 주인이 team이 아니기 때문에 정상적으로 실행될 수 없다.
            jpa는 연관관계의 주인만 create, update 할 수 있다.
             
            Member member = new Member();
            member.setUsername("member2");
            em.persist(member);

            Team team = new Team();
            team.setName("TeamA");
            team.getMembers().add(member);
            em.persist(team);
            */

            em.flush();
            em.clear();
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
