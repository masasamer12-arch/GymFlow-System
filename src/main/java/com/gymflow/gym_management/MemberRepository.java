package com.gymflow.gym_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // حذف سجلات الحضور يدوياً من قاعدة البيانات لهذا المشترك
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM attendance WHERE member_id = ?1", nativeQuery = true)
    void deleteAttendanceByMemberId(Long memberId);

    // حذف سجلات الاشتراكات يدوياً من قاعدة البيانات لهذا المشترك
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM subscriptions WHERE member_id = ?1", nativeQuery = true)
    void deleteSubscriptionsByMemberId(Long memberId);
}