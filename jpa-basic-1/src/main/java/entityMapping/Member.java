package entityMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import hellojpa.RoleType;

@Entity
@SequenceGenerator(
    name = "MEMBER_SEQ_GENERATOR", 
    sequenceName = "MEMBER_SEQ",//매핑할 데이터베이스 시퀀스 이름
    initialValue = 1,
    allocationSize = 50)
public class Member {

    @Id//pk 매핑
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY이면 db에 맞김, 
    //@GeneratedValue(strategy = GenerationType.AUTO) // Auto이면 db방언에 맞게 자동 설정
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name")//db 컬럼 name, 객체명 username
    private String username;

    public Member(){}

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getUserName(){return username;}
    public void setUserName(String userName){this.username = userName;}

    /*
    private Integer age;

    @Enumerated(EnumType.STRING)//java 에서 enum을 쓰고싶으면 해당 어노테이션 사용하면 됨
    //EnumType 의 default 타입인 ORDINAL 를 사용하면 db에 enum의 순서가 들어가게 되어있음. 그럼 enum의 순서가 바뀌거나 추가되면 큰 오류가 발생할 수 있음.
    //String 으로 하면 enum 문자열 그대로 입력된다.
    private RoleType  roleType;

    //시간은 이렇게 사용하면 됨.
    //private LocalDate localDate; //날짜까지
    //private LocalDateTime localDateTime; //날짜시간까지

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob//데이터베이스 varchar를 넘어서는 큰 타입
     private String description;
    */

}
