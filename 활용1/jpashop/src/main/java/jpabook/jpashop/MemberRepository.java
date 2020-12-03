package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository //자동 스프링빈 등록해줌 @autowired로 자동주입가능
public class MemberRepository {

    @PersistenceContext // 스프링 부트가 엔티티매니저 자동생성
    private EntityManager em;

    //저장하는 메서드
    public Long save(Member member){
        em.persist(member);  //EntityManager에 멤버저장 - 테이블에 저장
        return member.getId(); //아이디만 반환해서 조회에 사용
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
