package jpa_basic_2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            //저장
            Team team = new Team();
            team.setName("TeamB");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Member2");
            member.changeTeam(team);
            em.persist(member);

            //영속성 컨텍스트 1차 캐시 말고 직접 db 쿼리를 보고 싶으면
            em.flush();
            em.clear();

            //조회
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members =  findMember.getTeam().getMembers();

            for(Member m : members){
                System.out.println("m = " + m.getUsername());
            }

            //Team findTeam = findMember.getTeam();
            //System.out.println("findTeam: "+findTeam.getName());


            //수정
            //Team newTeam = em.find(Team.class, 100L);
            //findMember.setTeam(newTeam);

            /* 
            객체를 테이블에 맞추어 데이터 중심으로 모델링하면, 객체간의 협력 관계를 만들 수 없다.
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Member1");
            member.setTeamId(team.getId());
            em.persist(member);

            Member findMember = em.find(Member.class, member.getId());
            Long teamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, teamId);
            */

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