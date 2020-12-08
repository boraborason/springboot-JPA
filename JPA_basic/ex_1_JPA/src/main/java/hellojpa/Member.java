package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity//jpa에게 db와 연결되는 테이블로 인식하게함
public class Member {


    public Member() {
    }

    public Member(Long id, String name) { //객체 생성시 변수로 날려주면 바로 셋팅됨
        this.id = id;
        this.name = name;
    }

    @Id //jpa에게 pk를 알려줌
    private Long id;
    private  String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
