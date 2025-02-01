package com.ll.domain.member.member.service;

import com.ll.domain.member.member.entity.Member;
import com.ll.domain.member.member.repository.MemberRepository;
import com.ll.global.exceptions.ServiceException;
import com.ll.standard.search.MemberSearchKeywordTypeV1;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthTokenService authTokenService;

    public long count() {
        return memberRepository.count();
    }

    public Member join(String username, String password, String nickname, String profileImgUrl) {
        memberRepository
                .findByUsername(username)
                .ifPresent(_ -> {
                    throw new ServiceException("409-1", "해당 username은 이미 사용중입니다.");
                });


        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .apiKey(UUID.randomUUID().toString())
                .profileImgUrl(profileImgUrl)
                .build();

        return memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByApiKey(String apiKey) {
        return memberRepository.findByApiKey(apiKey);
    }

    public String getAccessToken(Member member) {
        return authTokenService.getAccessToken(member);
    }

    public String getAuthToken(Member member) {
        return member.getApiKey() + " " + getAccessToken(member);
    }

    public Member getMemberFromAccessToken(String accessToken) {
        Map<String, Object> payload = authTokenService.payload(accessToken);

        if(payload == null) return null;

        long id = (long) payload.get("id");
        String username = (String) payload.get("username");
        String nickname = (String) payload.get("nickname");

        Member member = new Member(id, username, nickname);

        return member;
    }

    public Page<Member> findByPaged(MemberSearchKeywordTypeV1 searchKeywordType, String searchKeyword, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));

        return memberRepository.findByKw(searchKeywordType, searchKeyword, pageable);
    }

    public Page<Member> findByPaged(int page, int pageSize) {
        return findByPaged(null, null, page, pageSize);
    }

    public void modify(Member member, String nickname, String profileImgUrl) {
        member.setNickname(nickname);
        member.setProfileImgUrl(profileImgUrl);
    }

    public Member modifyOrJoin(String username, String nickname, String profileImgUrl) {
        Optional<Member> opMember = findByUsername(username);

        if(opMember.isPresent()) {
            Member member = opMember.get();
            modify(member, nickname, profileImgUrl);

            return member;
        }

        return join(username, "", nickname, profileImgUrl);
    }
}
