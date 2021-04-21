package com.example.hellojpa.start;

import javax.persistence.*;  //**

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
//@org.hibernate.annotations.DynamicUpdate // 해당 어노테이션 적용시 수정된 데이터만 update 수정됨.
@Table(name="MEMBER")
public class Member {


    // ================== Field
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    private Integer age;

    // ================== Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
