package com.gymflow.gym_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*") 
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        System.out.println("استلام مشترك جديد: " + member.getFullName());
        return memberRepository.save(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member details) {
        return memberRepository.findById(id).map(member -> {
            member.setFullName(details.getFullName());
            member.setPhone(details.getPhone());
            member.setSubscriptionType(details.getSubscriptionType());
            member.setStartDate(details.getStartDate());
            member.setRemainingVisits(details.getRemainingVisits());
            member.setStatus(details.getStatus()); // أضفتها لضمان شمولية التعديل
            return ResponseEntity.ok(memberRepository.save(member));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        // الخطوة 1: تنظيف الجداول المرتبطة في قاعدة البيانات
        memberRepository.deleteAttendanceByMemberId(id);
        memberRepository.deleteSubscriptionsByMemberId(id);
        
        // الخطوة 2: حذف المشترك نفسه
        memberRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }
}