package com.example.hellojpa.start;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {


            tx.begin(); //트랜잭션 시작
            logic(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void logic(EntityManager em) {


        // ============================== 등록 start
        String id = "id003";
        Member member = new Member();
        member.setId(id);
        member.setUsername("시카고");
        member.setAge(2);
        // ==> 비영속상태


        //등록
        em.persist(member);
        // ==> 영속상태태
        // ============================= 등록 end

        // ============================== 수정 start
        //수정
        member.setAge(5);
        // ============================== 수정 end : entity값만 변경되어도 JPA가 추적하여 값을 수정한다.

        // ========================// 준영속 상태
//        em.detach(member); // 준영속 상태로 변경
//        em.clear(); // 영속성 컨테스트 초기화
//        em.close(); // 영속성 컨테스트 닫기
        // ==> 하나씩 확인해 보면, 아래 조회시에 전부 NullPointException / IllegalStateException 이 발생한다.

        //한 건 조회
        Member findMember = em.find(Member.class, id);
//        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
        System.out.println(findMember.toString());

        //목록 조회
//        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        TypedQuery<Member> query = em.createQuery("select mb from Member mb", Member.class);
        List<Member> members = query.getResultList();

        System.out.println("members.size=" + members.size());
        System.out.println("===// List");
        System.out.println(Arrays.toString(members.toArray()));


        //삭제
        em.remove(member);
        // 트랜젝션 커밋 전 삭제로 인해 DB에 데이터 없음음
    }

}