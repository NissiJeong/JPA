package jpa_basic_2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne //(fetch = FetchType.LAZY) FetchType.LAZY로 설정하면 Member와 Team select 쿼리가 분리돼서 실행된다. //멤버 입장에서는 멤버가 n, 팀이 1
    @JoinColumn(name = "TEAM_ID")//조인 컬럼 설정
    private Team team;

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

    public void setTeam(Team team) {
        this.team = team;
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
