package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증, 같은 이름이 있는 중복 회원X
        memberRepository.save(member);
        return member.getId();
    }

        /*
        본 코드는 지저분하니까 아래처럼 깔끔하게 바꿀 수 있다.
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { // 만약 여기 값이 있으면, -> Optional 로 감쌌기 때문에 사용 가능.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        그리고 이런거는 메소드로 따로 만들어줘서 하는 게 좋음.
        */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
