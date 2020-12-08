package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //persistence.xml의 unit네임, 설정 정보조회(db연결됨)
                                                                                                    //EntityManagerFactory반환
        EntityManager em = emf.createEntityManager(); //EntityManager생성하면 persistenceContext라는 영속성 컨텍스트 공간이 생성됨

        EntityTransaction tx= em.getTransaction(); //jpa에서는 변경하는 모든 작업이 tranjection이라는 단위가 반드시 필요함.
        tx.begin(); //tranjection 시작. 모든db변경은 이 안에서 이루어져야함

        try {
            //영속상태(DB에서 찾아서 컨텍스트에 올린상태)
            Member member = em.find(Member.class, 150L); //찾고
            member.setName("바꾸기");

            //비영속 상태
            em.detach(member); //update안됨 (특정 앤티티만)
            em.clear(); // (모든 앤티티)

            tx.commit(); //tranjection 커밋밋 (member객체가 db에 저장되는 시점)
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); //(영속성 컨텍스트 종료)
        }
        emf.close();
    }

}
