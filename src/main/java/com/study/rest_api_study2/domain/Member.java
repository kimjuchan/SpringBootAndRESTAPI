package com.study.rest_api_study2.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Member Entity
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "member")
public class Member {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String email;

    @Column
    private String userName;

    @Column
    private int age;

    //보안 데이터 가림 역할로 사용
    //@JsonIgnore
    @Column
    private String phoneNumber;

    @Column
    private String marriedYn;

}
