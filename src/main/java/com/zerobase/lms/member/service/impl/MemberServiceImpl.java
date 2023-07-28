package com.zerobase.lms.member.service.impl;

import com.zerobase.lms.admin.dto.MemberDto;
import com.zerobase.lms.admin.mapper.MemberMapper;
import com.zerobase.lms.admin.model.MemberParam;
import com.zerobase.lms.components.MailComponents;
import com.zerobase.lms.member.entity.Member;
import com.zerobase.lms.member.exception.MemberNotEmailAuthException;
import com.zerobase.lms.member.model.MemberInput;
import com.zerobase.lms.member.model.ResetPasswordInput;
import com.zerobase.lms.member.repository.MemberRepository;
import com.zerobase.lms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    private final MemberMapper memberMapper;

    /**
     * 회원 가입
     */
    @Override
    public boolean register(MemberInput parameter) {
        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        if (optionalMember.isPresent()) {
            // 현재 userId에 해당하는 데이터 존재
            return false;
        }

        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .userName(parameter.getUserName())
                .phone(parameter.getPhone())
                .password(encPassword)
                .regDt(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .build();
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "LMS 사이트 가입을 축하드립니다.";
        String text = "<p>LMS 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>"
                + "<div><a target'_blank' href='http://localhost:8080/member/email-auth?id=" + uuid + "'>가입 완료</a></div>";
        mailComponents.sendMail(email, subject, text);
        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if (!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();

        if (member.isEmailAuthYn()) {
            return false;
        }

        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);
        return true;
    }

    @Override
    public boolean sendResetPassword(ResetPasswordInput parameter) {
        Optional<Member> optionalMember = memberRepository.findByUserIdAndUserName(parameter.getUserId(), parameter.getUserName());
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        String uuid = UUID.randomUUID().toString();
        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusDays(1));
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "[LMS] 비밀번호 초기화 메일 입니다.";
        String text = "<p>LMS 비밀번호 초기화 메일 입니다.</p>" +
                "<p>아래 링크를 클릭하셔서 비밀번호를 초기화 해주세요.</p>" +
                "<div><a target'_blank' href='http://localhost:8080/member/reset/password?id=" + uuid + "'>비밀번호 초기화 링크</a></div>";
        mailComponents.sendMail(email, subject, text);
        return true;
    }

    @Override
    public boolean resetPassword(String uuid, String password) {
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        // 초기화 날짜가 유효한지 체크
        if (member.getResetPasswordLimitDt() == null) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }
        if (member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        member.setPassword(encPassword);
        member.setResetPasswordKey("");
        member.setResetPasswordLimitDt(null);
        memberRepository.save(member);
        return true;
    }

    @Override
    public boolean checkResetPassword(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByResetPasswordKey(uuid);
        if (!optionalMember.isPresent()) {
            return false;
        }

        Member member = optionalMember.get();

        // 초기화 날짜가 유효한지 체크
        if (member.getResetPasswordLimitDt() == null) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }
        if (member.getResetPasswordLimitDt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("유효한 날짜가 아닙니다.");
        }
        return true;
    }

    @Override
    public List<MemberDto> list(MemberParam parameter) {
        long totalCount = memberMapper.selectListCount(parameter);
        List<MemberDto> list = memberMapper.selectList(parameter);

        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (MemberDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    @Override
    public MemberDto detail(String userId) {
        Optional<Member> optionalMember = memberRepository.findById(userId);

        if (!optionalMember.isPresent()) {
            return null;
        }

        Member member = optionalMember.get();
        return MemberDto.of(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findById(username);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        if (!member.isEmailAuthYn()) {
            throw new MemberNotEmailAuthException("이메일 활성화 이후에 로그인 해주세요.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (member.isAdminYn()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);
    }
}
