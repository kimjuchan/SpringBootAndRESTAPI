package com.study.rest_api_study2.repository;

import com.study.rest_api_study2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
}
