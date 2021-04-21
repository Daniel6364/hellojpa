package com.example.hellojpa.start;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {
/* test : ~p115
    public static void logic(EntityManager em) {


        // ============================== 등록 start
        String id = "id003";
        Member memberA = new Member();
        memberA.setId(id);
        memberA.setUsername("시카고");
        memberA.setAge(2);
        // ==> 비영속상태

        Member memberB = new Member();
        memberB.setId("id004");
        memberB.setUsername("미네소타");
        memberB.setAge(3);

        Member memberC = new Member();
        memberC.setId("id005");
        memberC.setUsername("샌프란시스코");
        memberC.setAge(4);


        //등록
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        // ==> 영속상태태
        // ============================= 등록 end

        TypedQuery<Member> queryTest = em.createQuery("select m from Member m", Member.class);
        List<Member> membersTest = queryTest.getResultList();
        System.out.println("===// test check");
        System.out.println(Arrays.toString(membersTest.toArray()));


        // ============================== 수정 start
        //수정
        memberA.setAge(108);
        // ============================== 수정 end : entity값만 변경되어도 JPA가 추적하여 값을 수정한다.

        // ========================// 준영속 상태
//        em.detach(member); // 준영속 상태로 변경
//        em.clear(); // 영속성 컨테스트 초기화
//        em.close(); // 영속성 컨테스트 닫기
        // ==> 하나씩 확인해 보면, 아래 조회시에 전부 NullPointException / IllegalStateException 이 발생한다.

        //한 건 조회
        Member findMember1 = em.find(Member.class, id);
//        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
        System.out.println("=====// 1 : " + findMember1.toString());

        Member findMember2 = em.find(Member.class, id);
        System.out.println("=====// 2 : " + findMember2.toString());

        // 동일성 비교
        System.out.println(findMember1 == findMember2);


        //목록 조회
//        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
        List<Member> members = query.getResultList();

        System.out.println("members.size=" + members.size());
        System.out.println("===// List");
        System.out.println(Arrays.toString(members.toArray()));


        //삭제
        em.remove(memberA);
        em.remove(memberB);
        em.remove(memberC);
        // 트랜젝션 커밋 전 삭제로 인해 DB에 데이터 없음음
    }

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            // logic test
            //logic(em);  //비즈니스 로직

            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }


        emf.close(); //엔티티 매니저 팩토리 종료
    }
*/

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void mergeMember(Member member) {

        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        tx2.begin();

        Member mergeMember = em2.merge(member);
        tx2.commit();

        System.out.println("==// 1. member : " + member.getUsername());
        System.out.println("==// 2. mergeMember : " + mergeMember.getUsername());
        System.out.println("==// 3. em2.contiains member : " + em2.contains(member));
        System.out.println("==// 4. em2.contiains mergeMember : " + em2.contains(mergeMember));


    }

    public static Member createMember(String id, String username) {
        EntityManager em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);

        em1.persist(member);
        tx1.commit();

        em1.close();

        return member;
    }

    public static void main(String[] args) {

        Member member = createMember("id123", "LasVegas");
        member.setUsername("Sandiego");
        mergeMember(member);

    }


}