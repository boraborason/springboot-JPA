package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest //스프링부트로 테스트
public class MemberRepositoryTest
{
    @Autowired //각 상황에 맞는 loc컨테이너에 존재하는 bean을 자동으로 주입해준다.
    MemberRepository memberRepository; //인젝션 받음

    @Transactional //test에 있을 시 롤백해버려서 db에는 남지않음
    @Test
     //엔티티매니저를 통한 데이터 변경은 transaction을 필수 거쳐야 한다.
    @Rollback(false)	//테스트 종료 후 데이터를 롤배하지 않고 그대로 남겨두는 옵션
    public void testMember() throws Exception
    {
        //given
        Member member = new Member();
        member.setUsername("보라");  // 이름지정

        //when
        Long savedId = memberRepository.save(member); //저장된 아이디를 뽑음
        Member findMember = memberRepository.find(savedId);

        //then
        assertEquals(findMember.getId(), member.getId());
        assertEquals(findMember.getUsername(), member.getUsername());
        assertEquals(findMember, member);

//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        System.out.println("findMember == member : " + (findMember == member));
        //같은 트랜젝션 안에서 아이디 값이 같으면 같은 엔티티로 식별됨 (기본편 보충)
    }
}