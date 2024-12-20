package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String city;
    private String street;
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}
