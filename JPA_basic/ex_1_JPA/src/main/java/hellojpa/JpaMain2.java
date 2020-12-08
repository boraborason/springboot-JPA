package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //persistence.xml의 unit네임, 설정 정보조회(db연결됨)
                                                                                                    //EntityManagerFactory반환
        EntityManager em = emf.createEntityManager(); //EntityManager생성하면 persistenceContext라는 영속성 컨텍스트 공간이 생성됨

        EntityTransaction tx= em.getTransaction(); //jpa에서는 변경하는 모든 작업이 tranjection이라는 단위가 반드시 필요함.
        tx.begin(); //tranjection 시작. 모든db변경은 이 안에서 이루어져야함

        try {

            //비영속상태
            Member member = new Member();
            member.setId(11L);
            member.setName("송이");

            //영속상태(EntityManager를 통해 persistenceContext에서 관리가 되는 상태)
            em.persist(member); //member객체가 저장이 됨(아직 db저장x)

            Member findMember = em.find(Member.class, 11L);

            System.out.println(findMember.getName());

            tx.commit(); //tranjection 커밋밋 (member객체가 db에 저장되는 시점)
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
