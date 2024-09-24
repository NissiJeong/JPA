package entityMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hellojpa.Date;
import hellojpa.RoleType;

@Entity
public class Member {

    @Id//pk 매핑
    private Long id;

    @Column(name = "name")//db 컬럼 name, 객체명 username
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)//java 에서 enum을 쓰고싶으면 해당 어노테이션 사용하면 됨
    private RoleType  roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob//데이터베이스 varchar를 넘어서는 큰 타입
     private String description;

}
