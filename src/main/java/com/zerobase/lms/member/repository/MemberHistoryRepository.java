package com.zerobase.lms.member.repository;

import com.zerobase.lms.member.entity.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
}
