package jpa_basic_2;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("A")// 자식 클래스에 있으며, 어노테이션이 없으면 entity 명으로 insert 되지만 해당 어노테이션으로 insert 되는 값을 바꿔줄 수 있음.
public class Movie extends Item {
    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getActor() {
        return actor;
    }
    public void setActor(String actor) {
        this.actor = actor;
    }
}
