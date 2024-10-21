package jpa_basic_2;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne //멤버 입장에서는 멤버가 n, 팀이 1 //(fetch = FetchType.LAZY) FetchType.LAZY로 설정하면 Member와 Team select 쿼리가 분리돼서 실행된다. 
    @JoinColumn(name = "TEAM_ID")//조인 컬럼 설정
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    // Member와 Locker 와 1:1 관계.
    private Lockers locker; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        // 연관관계 편의 메소드를 이런 식으로 생성할 수 있다.
        team.getMembers().add(this);
    }

    /*
    해당 코드는 데이터의 관계에만 중점을 맞추어 객체지향 스럽지 않은 설계가 된 코드.
    @Column(name = "TEAM_ID")
    private Long teamId;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
    */
    
}
