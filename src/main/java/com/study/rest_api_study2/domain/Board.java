package com.study.rest_api_study2.domain;


import com.study.rest_api_study2.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer baordId;
    private String boardTitle;
    private String boardContent;


}
