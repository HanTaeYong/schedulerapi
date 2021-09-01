package com.scheduler.api.domain.member.entity;

import com.scheduler.api.common.model.jenum.ColDel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserIdAndIsDel(String userId, ColDel isDel);
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByEmailAndName(String email, String name);
}
