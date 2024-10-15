package jpa_basic_2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;

    String name;

    @OneToOne(mappedBy = "locker")
    private Member member;
}
