package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

/*  시간 로직
        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); // 중복 회원 검증, 같은 이름이 있는 중복 회원X
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            Long timeMs = finish = start;
            System.out.println("join = " + timeMs + "ms");
        }
 */

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

/* 시간 로직
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish = start;
            System.out.println("findMembers " + timeMs + "ms");
        }
*/
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
