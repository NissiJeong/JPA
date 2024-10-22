package jpa_basic_2.entityMapping;

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
            Member2 member = new Member2();
            member.setUsername("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            // 객체에서 외래 키는 Team 객체에서 관리하지만 db 테이블 입장에서는 1(Member) 에서 외래 키를 관리하니
            // update 문이 다시 실행될 수 밖에 없음.
            team.getMembers().add(member);

            em.persist(team);

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